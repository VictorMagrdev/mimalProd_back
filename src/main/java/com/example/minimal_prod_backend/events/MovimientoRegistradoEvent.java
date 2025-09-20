package com.example.minimal_prod_backend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MovimientoRegistradoEvent extends ApplicationEvent {
    private final Long movimientoId;

    public MovimientoRegistradoEvent(Object source, Long movimientoId) {
        super(source);
        this.movimientoId = movimientoId;
    }
}
