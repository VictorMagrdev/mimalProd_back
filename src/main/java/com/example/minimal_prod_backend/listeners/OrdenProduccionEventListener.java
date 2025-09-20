package com.example.minimal_prod_backend.listeners;

import com.example.minimal_prod_backend.dto.MovimientoInventarioInput;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.events.OrdenAvanzaEstacionEvent;
import com.example.minimal_prod_backend.events.OrdenCreadaEvent;
import com.example.minimal_prod_backend.events.OrdenFinalizadaEvent;
import com.example.minimal_prod_backend.events.OrdenIniciadaEvent;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.MovimientoInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrdenProduccionEventListener {

    private final ApplicationEventPublisher eventPublisher;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final OrdenEventoRepository ordenEventoRepository;
    private final OrdenEstacionRepository ordenEstacionRepository;
    private final ReservaMaterialOrdenRepository reservaMaterialOrdenRepository;
    private final EstacionProduccionRepository estacionProduccionRepository;
    private final MovimientoInventarioService movimientoInventarioService;
    private final TipoMovimientoRepository tipoMovimientoRepository;
    private final EstadoOrdenRepository estadoOrdenRepository;

    @EventListener
    @Transactional
    public void handleOrdenCreada(OrdenCreadaEvent event) {
        OrdenProduccion orden = ordenProduccionRepository.findById(event.getOrdenProduccionId())
                .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion con id " + event.getOrdenProduccionId() + " no encontrada"));

        LoteProduccion lote = new LoteProduccion();
        lote.setNumeroLote("LOTE-" + orden.getId() + "-" + LocalDateTime.now().getNano());
        lote.setProducto(orden.getProducto());
        lote.setCreadoEn(LocalDateTime.now());
        loteProduccionRepository.save(lote);

        OrdenEvento ordenEvento = OrdenEvento.builder()
                .orden(orden)
                .evento("Orden Creada")
                .descripcion("La orden de producción ha sido creada.")
                .fecha(LocalDateTime.now())
                .build();
        ordenEventoRepository.save(ordenEvento);
    }

    @EventListener
    @Transactional
    public void handleOrdenIniciada(OrdenIniciadaEvent event) {
        OrdenProduccion orden = ordenProduccionRepository.findById(event.getOrdenProduccionId())
                .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion con id " + event.getOrdenProduccionId() + " no encontrada"));

        EstacionProduccion primeraEstacion = estacionProduccionRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No se encontraron estaciones de producción"));

        OrdenEstacion ordenEstacion = OrdenEstacion.builder()
                .orden(orden)
                .estacion(primeraEstacion)
                .inicioReal(LocalDateTime.now())
                .estado("En Proceso")
                .build();
        ordenEstacionRepository.save(ordenEstacion);

        ReservaMaterialOrden reserva = ReservaMaterialOrden.builder()
                .orden(orden)
                .producto(orden.getProducto())
                .cantidadReservada(BigDecimal.TEN)
                .fechaReserva(LocalDateTime.now())
                .build();
        reservaMaterialOrdenRepository.save(reserva);

        OrdenEvento ordenEvento = OrdenEvento.builder()
                .orden(orden)
                .evento("Orden Iniciada")
                .descripcion("Inicia en estación: " + primeraEstacion.getNombre())
                .fecha(LocalDateTime.now())
                .build();
        ordenEventoRepository.save(ordenEvento);
    }

    @EventListener
    @Transactional
    public void handleOrdenAvanzaEstacion(OrdenAvanzaEstacionEvent event) {
        OrdenEstacion estacionActual = ordenEstacionRepository.findById(event.getOrdenEstacionId())
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEstacion con id " + event.getOrdenEstacionId() + " no encontrada"));

        estacionActual.setFinReal(LocalDateTime.now());
        estacionActual.setEstado("Finalizada");
        ordenEstacionRepository.save(estacionActual);

        TipoMovimiento tipoMovimiento = tipoMovimientoRepository.findByNombre("Consumo por Producción")
                .orElseThrow(() -> new ResourceNotFoundException("TipoMovimiento 'Consumo por Producción' no encontrado"));

        MovimientoInventarioInput movimientoInput = new MovimientoInventarioInput();
        movimientoInput.setIdTipoMovimiento(tipoMovimiento.getId());
        movimientoInput.setObservaciones("Consumo en estación " + estacionActual.getEstacion().getNombre());
        movimientoInput.setCreadoPor(1L); // TODO: inyectar usuario actual
        movimientoInventarioService.createMovimientoInventario(movimientoInput);

        eventPublisher.publishEvent(new OrdenFinalizadaEvent(this, estacionActual.getOrden().getId()));
    }

    @EventListener
    @Transactional
    public void handleOrdenFinalizada(OrdenFinalizadaEvent event) {
        OrdenProduccion orden = ordenProduccionRepository.findById(event.getOrdenProduccionId())
                .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion con id " + event.getOrdenProduccionId() + " no encontrada"));

        TipoMovimiento tipoMovimiento = tipoMovimientoRepository.findByNombre("Ingreso de Producción")
                .orElseThrow(() -> new ResourceNotFoundException("TipoMovimiento 'Ingreso de Producción' no encontrado"));

        MovimientoInventarioInput movimientoInput = new MovimientoInventarioInput();
        movimientoInput.setIdTipoMovimiento(tipoMovimiento.getId());
        movimientoInput.setObservaciones("Ingreso de producto terminado por orden " + orden.getId());
        movimientoInput.setCreadoPor(1L); // TODO: inyectar usuario actual
        movimientoInventarioService.createMovimientoInventario(movimientoInput);

        EstadoOrden estadoFinalizada = estadoOrdenRepository.findByNombre("Finalizada")
                .orElseThrow(() -> new ResourceNotFoundException("EstadoOrden 'Finalizada' no encontrado"));
        orden.setEstado(estadoFinalizada);
        orden.setFinReal(LocalDateTime.now());
        ordenProduccionRepository.save(orden);

        OrdenEvento ordenEvento = OrdenEvento.builder()
                .orden(orden)
                .evento("Orden Finalizada")
                .descripcion("La orden ha sido completada.")
                .fecha(LocalDateTime.now())
                .build();
        ordenEventoRepository.save(ordenEvento);
    }

}
