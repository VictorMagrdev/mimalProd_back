package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.entity.LoteProduccion;
import com.example.minimal_prod_backend.service.LoteProduccionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LoteProduccionController {

    private final LoteProduccionService loteProduccionService;

    public LoteProduccionController(LoteProduccionService loteProduccionService) {
        this.loteProduccionService = loteProduccionService;
    }

    @QueryMapping
    @PreAuthorize("hasPermission('LOTE_PRODUCCION', 'READ')")
    public List<LoteProduccion> lotesProduccion() {
        return loteProduccionService.getLotesProduccion();
    }

    @QueryMapping
    @PreAuthorize("hasPermission('LOTE_PRODUCCION', 'READ')")
    public LoteProduccion loteProduccion(@Argument Long id) {
        return loteProduccionService.getLoteProduccionById(id);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('LOTE_PRODUCCION', 'CREATE')")
    public LoteProduccion createLoteProduccion(@Argument LoteProduccion loteProduccion) {
        return loteProduccionService.createLoteProduccion(loteProduccion);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('LOTE_PRODUCCION', 'UPDATE')")
    public LoteProduccion updateLoteProduccion(@Argument Long id, @Argument LoteProduccion loteProduccion) {
        return loteProduccionService.updateLoteProduccion(id, loteProduccion);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('LOTE_PRODUCCION', 'DELETE')")
    public boolean deleteLoteProduccion(@Argument Long id) {
        loteProduccionService.deleteLoteProduccion(id);
        return true;
    }
}
