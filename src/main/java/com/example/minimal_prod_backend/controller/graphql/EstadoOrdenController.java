package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.EstadoOrdenInput;
import com.example.minimal_prod_backend.dto.EstadoOrdenResponse;
import com.example.minimal_prod_backend.service.EstadoOrdenService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EstadoOrdenController {

    private final EstadoOrdenService estadoOrdenService;

    public EstadoOrdenController(EstadoOrdenService estadoOrdenService) {
        this.estadoOrdenService = estadoOrdenService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADO_ORDEN_TAG', 'READ')")
    public List<EstadoOrdenResponse> estadosOrden() {
        return estadoOrdenService.getEstadosOrden();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADO_ORDEN_TAG', 'READ')")
    public EstadoOrdenResponse estadoOrden(@Argument Long id) {
        return estadoOrdenService.getEstadoOrdenById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADO_ORDEN_TAG', 'CREATE')")
    public EstadoOrdenResponse createEstadoOrden(@Argument("input") EstadoOrdenInput estadoOrdenInput) {
        return estadoOrdenService.createEstadoOrden(estadoOrdenInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADO_ORDEN_TAG', 'UPDATE')")
    public EstadoOrdenResponse updateEstadoOrden(@Argument Long id, @Argument("input") EstadoOrdenInput estadoOrdenInput) {
        return estadoOrdenService.updateEstadoOrden(id, estadoOrdenInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADO_ORDEN_TAG', 'DELETE')")
    public boolean deleteEstadoOrden(@Argument Long id) {
        estadoOrdenService.deleteEstadoOrden(id);
        return true;
    }
}
