package com.example.minimal_prod_backend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrdenFinalizadaEvent extends ApplicationEvent {
    private final Long ordenProduccionId;

    public OrdenFinalizadaEvent(Object source, Long ordenProduccionId) {
        super(source);
        this.ordenProduccionId = ordenProduccionId;
    }

}
