package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.entity.UnidadMedida;
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
    @PreAuthorize("@customSecurity.hasPermission('UNIDAD_MEDIDA_TAG', 'READ')")
    public List<UnidadMedida> unidadesMedida() {
        return unidadMedidaService.getUnidadesMedida();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDAD_MEDIDA_TAG', 'READ')")
    public UnidadMedida unidadMedida(@Argument Long id) {
        return unidadMedidaService.getUnidadMedidaById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDAD_MEDIDA_TAG', 'CREATE')")
    public UnidadMedida createUnidadMedida(@Argument UnidadMedida unidadMedida) {
        return unidadMedidaService.createUnidadMedida(unidadMedida);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDAD_MEDIDA_TAG', 'UPDATE')")
    public UnidadMedida updateUnidadMedida(@Argument Long id, @Argument UnidadMedida unidadMedida) {
        return unidadMedidaService.updateUnidadMedida(id, unidadMedida);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDAD_MEDIDA_TAG', 'DELETE')")
    public boolean deleteUnidadMedida(@Argument Long id) {
        unidadMedidaService.deleteUnidadMedida(id);
        return true;
    }
}
