package com.example.minimal_prod_backend.listeners;

import com.example.minimal_prod_backend.events.StockBajoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificacionEventListener {

    @EventListener
    public void handleStockBajo(StockBajoEvent event) {
        log.warn("ALERTA: Stock bajo para el lote de inventario ID: {}", event.getInventarioLoteId());
        // Lógica para enviar una notificación, correo electrónico, o crear una tarea para el equipo de compras/producción.
    }
}
