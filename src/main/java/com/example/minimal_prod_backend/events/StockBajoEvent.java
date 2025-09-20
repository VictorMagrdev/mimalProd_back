package com.example.minimal_prod_backend.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StockBajoEvent extends ApplicationEvent {
    private final Long inventarioLoteId;

    public StockBajoEvent(Object source, Long inventarioLoteId) {
        super(source);
        this.inventarioLoteId = inventarioLoteId;
    }
}
