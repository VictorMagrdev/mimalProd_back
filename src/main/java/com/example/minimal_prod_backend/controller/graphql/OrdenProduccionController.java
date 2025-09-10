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
    @PreAuthorize("@customSecurity.hasPermission('ORDEN_PRODUCCION_TAG', 'READ')")
    public List<OrdenProduccion> ordenesProduccion() {
        return ordenProduccionService.getOrdenesProduccion();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDEN_PRODUCCION_TAG', 'READ')")
    public OrdenProduccion ordenProduccion(@Argument Long id) {
        return ordenProduccionService.getOrdenProduccionById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDEN_PRODUCCION_TAG', 'CREATE')")
    public OrdenProduccion createOrdenProduccion(@Argument OrdenProduccion ordenProduccion) {
        return ordenProduccionService.createOrdenProduccion(ordenProduccion);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDEN_PRODUCCION_TAG', 'UPDATE')")
    public OrdenProduccion updateOrdenProduccion(@Argument Long id, @Argument OrdenProduccion ordenProduccion) {
        return ordenProduccionService.updateOrdenProduccion(id, ordenProduccion);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDEN_PRODUCCION_TAG', 'DELETE')")
    public boolean deleteOrdenProduccion(@Argument Long id) {
        ordenProduccionService.deleteOrdenProduccion(id);
        return true;
    }
}
