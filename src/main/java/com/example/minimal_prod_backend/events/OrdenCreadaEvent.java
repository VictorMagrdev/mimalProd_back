package com.example.minimal_prod_backend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrdenCreadaEvent extends ApplicationEvent {
    private final Long ordenProduccionId;

    public OrdenCreadaEvent(Object source, Long ordenProduccionId) {
        super(source);
        this.ordenProduccionId = ordenProduccionId;
    }
}
