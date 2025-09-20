package com.example.minimal_prod_backend.listeners;

import com.example.minimal_prod_backend.entity.ConteoCiclico;
import com.example.minimal_prod_backend.entity.DiscrepanciaInventario;
import com.example.minimal_prod_backend.entity.InventarioLote;
import com.example.minimal_prod_backend.events.ConteoEjecutadoEvent;
import com.example.minimal_prod_backend.events.DiscrepanciaDetectadaEvent;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.ConteoCiclicoRepository;
import com.example.minimal_prod_backend.repository.DiscrepanciaInventarioRepository;
import com.example.minimal_prod_backend.repository.InventarioLoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConteoCiclicoEventListener {

    private final ApplicationEventPublisher eventPublisher;
    private final ConteoCiclicoRepository conteoCiclicoRepository;
    private final InventarioLoteRepository inventarioLoteRepository;
    private final DiscrepanciaInventarioRepository discrepanciaInventarioRepository;

    @EventListener
    @Transactional
    public void handleConteoEjecutado(ConteoEjecutadoEvent event) {
        ConteoCiclico conteo = conteoCiclicoRepository.findById(event.getConteoCiclicoId())
                .orElseThrow(() -> new ResourceNotFoundException( "ConteoCiclico no encontrado con id = " + event.getConteoCiclicoId()));

        InventarioLote loteSistema = inventarioLoteRepository
                .findByProductoAndLoteAndBodega(conteo.getProducto(), conteo.getLote(), conteo.getBodega())
                .orElse(new InventarioLote()); // Si no existe, la cantidad en sistema es 0

        BigDecimal cantidadSistema = loteSistema.getCantidad() != null ? loteSistema.getCantidad() : BigDecimal.ZERO;

        if (cantidadSistema.compareTo(conteo.getCantidadContada()) != 0) {
            DiscrepanciaInventario discrepancia = DiscrepanciaInventario.builder()
                    .conteo(conteo)
                    .cantidadSistema(cantidadSistema)
                    .cantidadContada(conteo.getCantidadContada())
                    .resuelto(false)
                    .build();
            DiscrepanciaInventario savedDiscrepancia = discrepanciaInventarioRepository.save(discrepancia);

            eventPublisher.publishEvent(new DiscrepanciaDetectadaEvent(this, savedDiscrepancia.getId()));
        }
    }

    @EventListener
    public void handleDiscrepanciaDetectada(DiscrepanciaDetectadaEvent event) {
        discrepanciaInventarioRepository.findById(event.getDiscrepanciaId()).ifPresent(d -> {
            log.warn("Discrepancia de inventario detectada. ID: {}. Producto: {}. Sistema: {}, Contado: {}",
                    d.getId(),
                    d.getConteo().getProducto().getNombre(),
                    d.getCantidadSistema(),
                    d.getCantidadContada());
            // Aquí se podría iniciar un workflow de ajuste, enviar notificaciones, etc.
        });
    }
}
