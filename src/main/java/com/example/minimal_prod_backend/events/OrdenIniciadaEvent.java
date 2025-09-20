package com.example.minimal_prod_backend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrdenIniciadaEvent extends ApplicationEvent {
    private final Long ordenProduccionId;

    public OrdenIniciadaEvent(Object source, Long ordenProduccionId) {
        super(source);
        this.ordenProduccionId = ordenProduccionId;
    }
}
