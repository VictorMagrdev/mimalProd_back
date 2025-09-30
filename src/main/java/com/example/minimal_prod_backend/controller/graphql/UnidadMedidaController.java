package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.UnidadMedidaInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.service.UnidadMedidaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

    public UnidadMedidaController(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_MEDIDA_TAG', 'READ')")
    public List<UnidadMedidaResponse> unidadesMedida() {
        return unidadMedidaService.getUnidadesMedida();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_MEDIDA_TAG', 'READ')")
    public UnidadMedidaResponse unidadMedida(@Argument Long id) {
        return unidadMedidaService.getUnidadMedidaById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_MEDIDA_TAG', 'CREATE')")
    public UnidadMedidaResponse createUnidadMedida(@Argument("input") UnidadMedidaInput unidadMedidaInput) {
        return unidadMedidaService.createUnidadMedida(unidadMedidaInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_MEDIDA_TAG', 'UPDATE')")
    public UnidadMedidaResponse updateUnidadMedida(@Argument Long id, @Argument("input") UnidadMedidaInput unidadMedidaInput) {
        return unidadMedidaService.updateUnidadMedida(id, unidadMedidaInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_MEDIDA_TAG', 'DELETE')")
    public boolean deleteUnidadMedida(@Argument Long id) {
        unidadMedidaService.deleteUnidadMedida(id);
        return true;
    }
}
