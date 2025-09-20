package com.example.minimal_prod_backend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrdenAvanzaEstacionEvent extends ApplicationEvent {
    private final Long ordenEstacionId;

    public OrdenAvanzaEstacionEvent(Object source, Long ordenEstacionId) {
        super(source);
        this.ordenEstacionId = ordenEstacionId;
    }
}
