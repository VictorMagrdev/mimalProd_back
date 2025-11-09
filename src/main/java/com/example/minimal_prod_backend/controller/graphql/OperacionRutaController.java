package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.OperacionRutaRequest;
import com.example.minimal_prod_backend.dto.Response.OperacionRutaResponse;
import com.example.minimal_prod_backend.service.OperacionRutaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OperacionRutaController {

    private final OperacionRutaService operacionRutaService;

    public OperacionRutaController(OperacionRutaService operacionRutaService) {
        this.operacionRutaService = operacionRutaService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_RUTA_TAG', 'READ')")
    public List<OperacionRutaResponse> operacionesRuta() {
        return operacionRutaService.findAll();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_RUTA_TAG', 'READ')")
    public OperacionRutaResponse operacionRuta(@Argument Long id) {
        return operacionRutaService.findById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_RUTA_TAG', 'CREATE')")
    public OperacionRutaResponse createOperacionRuta(@Argument("input") OperacionRutaRequest operacionRutaInput) {
        return operacionRutaService.save(operacionRutaInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('OPERACIONES_RUTA_TAG', 'DELETE')")
    public boolean deleteOperacionRuta(@Argument Long id) {
        operacionRutaService.deleteById(id);
        return true;
    }
}