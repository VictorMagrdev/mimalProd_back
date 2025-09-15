package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.OrdenEventoInput;
import com.example.minimal_prod_backend.dto.OrdenEventoResponse;
import com.example.minimal_prod_backend.service.OrdenEventoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrdenEventoController {

    private final OrdenEventoService ordenEventoService;

    public OrdenEventoController(OrdenEventoService ordenEventoService) {
        this.ordenEventoService = ordenEventoService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDEN_EVENTO_TAG', 'READ')")
    public List<OrdenEventoResponse> ordenesEvento() {
        return ordenEventoService.getOrdenesEvento();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDEN_EVENTO_TAG', 'READ')")
    public OrdenEventoResponse ordenEvento(@Argument Long id) {
        return ordenEventoService.getOrdenEventoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDEN_EVENTO_TAG', 'CREATE')")
    public OrdenEventoResponse createOrdenEvento(@Argument("input") OrdenEventoInput ordenEventoInput) {
        return ordenEventoService.createOrdenEvento(ordenEventoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDEN_EVENTO_TAG', 'UPDATE')")
    public OrdenEventoResponse updateOrdenEvento(@Argument Long id, @Argument("input") OrdenEventoInput ordenEventoInput) {
        return ordenEventoService.updateOrdenEvento(id, ordenEventoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDEN_EVENTO_TAG', 'DELETE')")
    public boolean deleteOrdenEvento(@Argument Long id) {
        ordenEventoService.deleteOrdenEvento(id);
        return true;
    }
}
