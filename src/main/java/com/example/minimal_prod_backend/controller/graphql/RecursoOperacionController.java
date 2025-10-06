package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.RecursoOperacionRequest;
import com.example.minimal_prod_backend.dto.RecursoOperacionResponse;
import com.example.minimal_prod_backend.service.RecursoOperacionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RecursoOperacionController {

    private final RecursoOperacionService recursoOperacionService;

    public RecursoOperacionController(RecursoOperacionService recursoOperacionService) {
        this.recursoOperacionService = recursoOperacionService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('RECURSOS_OPERACION_TAG', 'READ')")
    public List<RecursoOperacionResponse> recursosOperacion() {
        return recursoOperacionService.findAll();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('RECURSOS_OPERACION_TAG', 'READ')")
    public RecursoOperacionResponse recursoOperacion(@Argument Long id) {
        return recursoOperacionService.findById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('RECURSOS_OPERACION_TAG', 'CREATE')")
    public RecursoOperacionResponse createRecursoOperacion(@Argument("input") RecursoOperacionRequest recursoOperacionInput) {
        return recursoOperacionService.save(recursoOperacionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('RECURSOS_OPERACION_TAG', 'DELETE')")
    public boolean deleteRecursoOperacion(@Argument Long id) {
        recursoOperacionService.deleteById(id);
        return true;
    }
}