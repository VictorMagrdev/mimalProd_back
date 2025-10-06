package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.OperacionOrdenRequest;
import com.example.minimal_prod_backend.dto.OperacionOrdenResponse;
import com.example.minimal_prod_backend.service.OperacionOrdenService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OperacionOrdenController {

    private final OperacionOrdenService operacionOrdenService;

    public OperacionOrdenController(OperacionOrdenService operacionOrdenService) {
        this.operacionOrdenService = operacionOrdenService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_ORDEN_TAG', 'READ')")
    public List<OperacionOrdenResponse> operacionesOrden() {
        return operacionOrdenService.findAll();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_ORDEN_TAG', 'READ')")
    public OperacionOrdenResponse operacionOrden(@Argument Long id) {
        return operacionOrdenService.findById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_ORDEN_TAG', 'CREATE')")
    public OperacionOrdenResponse createOperacionOrden(@Argument("input") OperacionOrdenRequest operacionOrdenInput) {
        return operacionOrdenService.save(operacionOrdenInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_ORDEN_TAG', 'DELETE')")
    public boolean deleteOperacionOrden(@Argument Long id) {
        operacionOrdenService.deleteById(id);
        return true;
    }
}