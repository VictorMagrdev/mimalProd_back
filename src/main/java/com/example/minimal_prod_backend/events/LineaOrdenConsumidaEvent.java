package com.example.minimal_prod_backend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LineaOrdenConsumidaEvent extends ApplicationEvent {
    private final Long lineaOrdenId;

    public LineaOrdenConsumidaEvent(Object source, Long lineaOrdenId) {
        super(source);
        this.lineaOrdenId = lineaOrdenId;
    }
}
