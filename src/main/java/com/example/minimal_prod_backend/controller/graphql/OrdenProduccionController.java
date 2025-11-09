package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.OrdenProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.OrdenProduccionResponse;
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
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_PRODUCCION_TAG', 'READ')")
    public List<OrdenProduccionResponse> ordenesProduccion() {
        return ordenProduccionService.getOrdenesProduccion();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_PRODUCCION_TAG', 'READ')")
    public OrdenProduccionResponse ordenProduccion(@Argument Long id) {
        return ordenProduccionService.getOrdenProduccionById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_PRODUCCION_TAG', 'CREATE')")
    public OrdenProduccionResponse createOrdenProduccion(@Argument("input") OrdenProduccionRequest ordenProduccionRequest) {
        return ordenProduccionService.createOrdenProduccion(ordenProduccionRequest);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_PRODUCCION_TAG', 'UPDATE')")
    public OrdenProduccionResponse updateOrdenProduccion(@Argument Long id, @Argument("input") OrdenProduccionRequest ordenProduccionRequest) {
        return ordenProduccionService.updateOrdenProduccion(id, ordenProduccionRequest);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_PRODUCCION_TAG', 'DELETE')")
    public boolean deleteOrdenProduccion(@Argument Long id) {
        ordenProduccionService.deleteOrdenProduccion(id);
        return true;
    }
}
