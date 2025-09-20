package com.example.minimal_prod_backend.listeners;

import com.example.minimal_prod_backend.entity.InventarioLote;
import com.example.minimal_prod_backend.entity.MovimientoInventario;
import com.example.minimal_prod_backend.entity.MovimientoInventarioDetalle;
import com.example.minimal_prod_backend.events.MovimientoRegistradoEvent;
import com.example.minimal_prod_backend.events.StockBajoEvent;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.DiscrepanciaInventarioRepository;
import com.example.minimal_prod_backend.repository.InventarioLoteRepository;
import com.example.minimal_prod_backend.repository.MovimientoInventarioRepository;
import com.example.minimal_prod_backend.repository.PuntoReordenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MovimientoInventarioEventListener {

    private final ApplicationEventPublisher eventPublisher;
    private final MovimientoInventarioRepository movimientoInventarioRepository;
    private final InventarioLoteRepository inventarioLoteRepository;
    private final PuntoReordenRepository puntoReordenRepository;
    private final DiscrepanciaInventarioRepository discrepanciaInventarioRepository;

    @EventListener
    @Transactional
    public void handleMovimientoRegistrado(MovimientoRegistradoEvent event) {
        MovimientoInventario movimiento = movimientoInventarioRepository.findById(event.getMovimientoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "MovimientoInventario no encontrado con id = " + event.getMovimientoId()
                ));

        // Procesar cada detalle del movimiento
        for (MovimientoInventarioDetalle detalle : movimiento.getDetalles()) {
            BigDecimal cantidadMovimiento = detalle.getCantidad() != null ? detalle.getCantidad() : BigDecimal.ZERO;

            if ("SALIDA".equalsIgnoreCase(movimiento.getTipoMovimiento().getNombre())) {
                cantidadMovimiento = cantidadMovimiento.negate();
            }

            InventarioLote lote = inventarioLoteRepository
                    .findByProductoAndLoteAndBodega(detalle.getProducto(), detalle.getLote(), movimiento.getBodegaDestino())
                    .orElseGet(() -> {
                        InventarioLote newLote = new InventarioLote();
                        newLote.setProducto(detalle.getProducto());
                        newLote.setLote(detalle.getLote());
                        newLote.setBodega(movimiento.getBodegaDestino());
                        newLote.setUnidad(detalle.getUnidad());
                        newLote.setCantidad(BigDecimal.ZERO);
                        return newLote;
                    });

            // Actualizar cantidades
            lote.setCantidad(lote.getCantidad().add(cantidadMovimiento));
            lote.setActualizadoEn(LocalDateTime.now());
            inventarioLoteRepository.save(lote);

            puntoReordenRepository.findByProductoAndBodega(lote.getProducto(), lote.getBodega())
                    .ifPresent(punto -> {
                        if (lote.getCantidad().compareTo(punto.getStockSeguridad()) < 0) {
                            eventPublisher.publishEvent(new StockBajoEvent(this, lote.getId()));
                        }
                    });
        }
    }

}
