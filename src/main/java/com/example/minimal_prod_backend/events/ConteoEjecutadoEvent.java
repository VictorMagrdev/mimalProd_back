package com.example.minimal_prod_backend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ConteoEjecutadoEvent extends ApplicationEvent {
    private final Long conteoCiclicoId;

    public ConteoEjecutadoEvent(Object source, Long conteoCiclicoId) {
        super(source);
        this.conteoCiclicoId = conteoCiclicoId;
    }
}
