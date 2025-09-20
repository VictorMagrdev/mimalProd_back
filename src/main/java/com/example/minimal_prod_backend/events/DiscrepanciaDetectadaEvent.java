package com.example.minimal_prod_backend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DiscrepanciaDetectadaEvent extends ApplicationEvent {
    private final Long discrepanciaId;

    public DiscrepanciaDetectadaEvent(Object source, Long discrepanciaId) {
        super(source);
        this.discrepanciaId = discrepanciaId;
    }
}
