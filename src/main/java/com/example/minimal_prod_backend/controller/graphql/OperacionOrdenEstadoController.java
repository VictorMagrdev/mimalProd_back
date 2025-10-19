package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.OperacionOrdenEstadoRequest;
import com.example.minimal_prod_backend.dto.OperacionOrdenEstadoResponse;
import com.example.minimal_prod_backend.service.OperacionOrdenEstadoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OperacionOrdenEstadoController {

    private final OperacionOrdenEstadoService operacionOrdenEstadoService;

    public OperacionOrdenEstadoController(OperacionOrdenEstadoService operacionOrdenEstadoService) {
        this.operacionOrdenEstadoService = operacionOrdenEstadoService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_ORDEN_ESTADOS_TAG', 'READ')")
    public List<OperacionOrdenEstadoResponse> operacionesOrdenEstado() {
        return operacionOrdenEstadoService.findAll();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_ORDEN_ESTADOS_TAG', 'READ')")
    public OperacionOrdenEstadoResponse operacionOrdenEstado(@Argument Long id) {
        return operacionOrdenEstadoService.findById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_ORDEN_ESTADOS_TAG', 'CREATE')")
    public OperacionOrdenEstadoResponse createEstadoOperacionOrden(@Argument OperacionOrdenEstadoRequest input) {
        return operacionOrdenEstadoService.save(input);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_ORDEN_ESTADOS_TAG', 'DELETE')")
    public boolean deleteEstadoOperacionOrden(@Argument Long id) {
        operacionOrdenEstadoService.deleteById(id);
        return true;
    }
}