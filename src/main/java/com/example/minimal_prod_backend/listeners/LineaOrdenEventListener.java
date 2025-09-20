package com.example.minimal_prod_backend.listeners;

import com.example.minimal_prod_backend.dto.MovimientoInventarioInput;
import com.example.minimal_prod_backend.entity.LineaOrden;
import com.example.minimal_prod_backend.entity.MovimientoInventario;
import com.example.minimal_prod_backend.entity.MovimientoInventarioDetalle;
import com.example.minimal_prod_backend.entity.ReservaMaterialOrden;
import com.example.minimal_prod_backend.entity.TipoMovimiento;
import com.example.minimal_prod_backend.events.LineaOrdenConsumidaEvent;
import com.example.minimal_prod_backend.events.LineaOrdenCreadaEvent;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.LineaOrdenRepository;
import com.example.minimal_prod_backend.repository.MovimientoInventarioDetalleRepository;
import com.example.minimal_prod_backend.repository.MovimientoInventarioRepository;
import com.example.minimal_prod_backend.repository.ReservaMaterialOrdenRepository;
import com.example.minimal_prod_backend.repository.TipoMovimientoRepository;
import com.example.minimal_prod_backend.service.MovimientoInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LineaOrdenEventListener {

    private final LineaOrdenRepository lineaOrdenRepository;
    private final ReservaMaterialOrdenRepository reservaMaterialOrdenRepository;
    private final MovimientoInventarioService movimientoInventarioService;
    private final TipoMovimientoRepository tipoMovimientoRepository;
    private final MovimientoInventarioDetalleRepository movimientoInventarioDetalleRepository;
    private final MovimientoInventarioRepository movimientoInventarioRepository;

    @EventListener
    @Transactional
    public void handleLineaOrdenCreada(LineaOrdenCreadaEvent event) {
        LineaOrden linea = lineaOrdenRepository.findById(event.getLineaOrdenId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "LineaOrden no encontrada con id = " + event.getLineaOrdenId()
                ));

        ReservaMaterialOrden reserva = ReservaMaterialOrden.builder()
                .orden(linea.getOrden())
                .producto(linea.getProductoComponente())
                .cantidadReservada(linea.getCantidadRequerida())
                .unidad(linea.getUnidadComponente())
                .fechaReserva(LocalDateTime.now())
                .build();

        reservaMaterialOrdenRepository.save(reserva);
    }

    @EventListener
    @Transactional
    public void handleLineaOrdenConsumida(LineaOrdenConsumidaEvent event) {
        LineaOrden linea = lineaOrdenRepository.findById(event.getLineaOrdenId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "LineaOrden no encontrada con id = " + event.getLineaOrdenId()
                ));

        TipoMovimiento tipoMovimiento = tipoMovimientoRepository.findByNombre("Salida a WIP")
                .orElseThrow(() -> new ResourceNotFoundException(
                        "TipoMovimiento 'Salida a WIP' no encontrado"
                ));

        MovimientoInventarioInput movimientoInput = new MovimientoInventarioInput();
        movimientoInput.setIdTipoMovimiento(tipoMovimiento.getId());
        movimientoInput.setObservaciones("Consumo Línea Orden: " + linea.getId()
                + " - " + (linea.getProductoComponente() != null ? linea.getProductoComponente().getNombre() : ""));
        movimientoInput.setCreadoPor(1L);

        var movimientoResponse = movimientoInventarioService.createMovimientoInventario(movimientoInput);

        MovimientoInventario movimientoRef = movimientoInventarioRepository.getReferenceById(movimientoResponse.getId());

        MovimientoInventarioDetalle detalle = MovimientoInventarioDetalle.builder()
                .movimiento(movimientoRef)
                .producto(linea.getProductoComponente())
                .cantidad(linea.getCantidadRequerida())
                .unidad(linea.getUnidadComponente())
                .build();

        movimientoInventarioDetalleRepository.save(detalle);
    }
}
