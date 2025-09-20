package com.example.minimal_prod_backend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LineaOrdenCreadaEvent extends ApplicationEvent {
    private final Long lineaOrdenId;

    public LineaOrdenCreadaEvent(Object source, Long lineaOrdenId) {
        super(source);
        this.lineaOrdenId = lineaOrdenId;
    }
}
