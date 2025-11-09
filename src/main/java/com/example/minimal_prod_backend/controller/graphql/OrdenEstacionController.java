package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.OrdenEstacionRequest;
import com.example.minimal_prod_backend.dto.Response.OrdenEstacionResponse;
import com.example.minimal_prod_backend.service.OrdenEstacionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrdenEstacionController {

    private final OrdenEstacionService ordenEstacionService;

    public OrdenEstacionController(OrdenEstacionService ordenEstacionService) {
        this.ordenEstacionService = ordenEstacionService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_ESTACION_TAG', 'READ')")
    public List<OrdenEstacionResponse> ordenesEstacion() {
        return ordenEstacionService.getOrdenesEstacion();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_ESTACION_TAG', 'READ')")
    public OrdenEstacionResponse ordenEstacion(@Argument Long id) {
        return ordenEstacionService.getOrdenEstacionById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_ESTACION_TAG', 'CREATE')")
    public OrdenEstacionResponse createOrdenEstacion(@Argument("input") OrdenEstacionRequest ordenEstacionInput) {
        return ordenEstacionService.createOrdenEstacion(ordenEstacionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_ESTACION_TAG', 'UPDATE')")
    public OrdenEstacionResponse updateOrdenEstacion(@Argument Long id, @Argument("input") OrdenEstacionRequest ordenEstacionInput) {
        return ordenEstacionService.updateOrdenEstacion(id, ordenEstacionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_ESTACION_TAG', 'DELETE')")
    public boolean deleteOrdenEstacion(@Argument Long id) {
        ordenEstacionService.deleteOrdenEstacion(id);
        return true;
    }
}
