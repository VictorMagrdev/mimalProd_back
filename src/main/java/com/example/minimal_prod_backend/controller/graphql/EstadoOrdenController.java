package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.entity.EstadoOrden;
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
    public List<EstadoOrden> estadosOrden() {
        return estadoOrdenService.getEstadosOrden();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADO_ORDEN_TAG', 'READ')")
    public EstadoOrden estadoOrden(@Argument Long id) {
        return estadoOrdenService.getEstadoOrdenById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADO_ORDEN_TAG', 'CREATE')")
    public EstadoOrden createEstadoOrden(@Argument EstadoOrden estadoOrden) {
        return estadoOrdenService.createEstadoOrden(estadoOrden);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADO_ORDEN_TAG', 'UPDATE')")
    public EstadoOrden updateEstadoOrden(@Argument Long id, @Argument EstadoOrden estadoOrden) {
        return estadoOrdenService.updateEstadoOrden(id, estadoOrden);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADO_ORDEN_TAG', 'DELETE')")
    public boolean deleteEstadoOrden(@Argument Long id) {
        estadoOrdenService.deleteEstadoOrden(id);
        return true;
    }
}
