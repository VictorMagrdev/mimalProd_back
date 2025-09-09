package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.service.OrdenProduccionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrdenProduccionController {

    private final OrdenProduccionService ordenProduccionService;

    public OrdenProduccionController(OrdenProduccionService ordenProduccionService) {
        this.ordenProduccionService = ordenProduccionService;
    }

    @QueryMapping
    @PreAuthorize("hasPermission('ORDEN_PRODUCCION', 'READ')")
    public List<OrdenProduccion> ordenesProduccion() {
        return ordenProduccionService.getOrdenesProduccion();
    }

    @QueryMapping
    @PreAuthorize("hasPermission('ORDEN_PRODUCCION', 'READ')")
    public OrdenProduccion ordenProduccion(@Argument Long id) {
        return ordenProduccionService.getOrdenProduccionById(id);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('ORDEN_PRODUCCION', 'CREATE')")
    public OrdenProduccion createOrdenProduccion(@Argument OrdenProduccion ordenProduccion) {
        return ordenProduccionService.createOrdenProduccion(ordenProduccion);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('ORDEN_PRODUCCION', 'UPDATE')")
    public OrdenProduccion updateOrdenProduccion(@Argument Long id, @Argument OrdenProduccion ordenProduccion) {
        return ordenProduccionService.updateOrdenProduccion(id, ordenProduccion);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('ORDEN_PRODUCCION', 'DELETE')")
    public boolean deleteOrdenProduccion(@Argument Long id) {
        ordenProduccionService.deleteOrdenProduccion(id);
        return true;
    }
}
