package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import com.example.minimal_prod_backend.service.UnidadMedidaTipoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UnidadMedidaTipoController {

    private final UnidadMedidaTipoService unidadMedidaTipoService;

    public UnidadMedidaTipoController(UnidadMedidaTipoService unidadMedidaTipoService) {
        this.unidadMedidaTipoService = unidadMedidaTipoService;
    }

    @QueryMapping
    @PreAuthorize("hasPermission('UNIDAD_MEDIDA_TIPO', 'READ')")
    public List<UnidadMedidaTipo> unidadesMedidaTipo() {
        return unidadMedidaTipoService.getUnidadMedidaTipos();
    }

    @QueryMapping
    @PreAuthorize("hasPermission('UNIDAD_MEDIDA_TIPO', 'READ')")
    public UnidadMedidaTipo unidadMedidaTipo(@Argument Long id) {
        return unidadMedidaTipoService.getUnidadMedidaTipoById(id);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('UNIDAD_MEDIDA_TIPO', 'CREATE')")
    public UnidadMedidaTipo createUnidadMedidaTipo(@Argument UnidadMedidaTipo unidadMedidaTipo) {
        return unidadMedidaTipoService.createUnidadMedidaTipo(unidadMedidaTipo);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('UNIDAD_MEDIDA_TIPO', 'UPDATE')")
    public UnidadMedidaTipo updateUnidadMedidaTipo(@Argument Long id, @Argument UnidadMedidaTipo unidadMedidaTipo) {
        return unidadMedidaTipoService.updateUnidadMedidaTipo(id, unidadMedidaTipo);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('UNIDAD_MEDIDA_TIPO', 'DELETE')")
    public boolean deleteUnidadMedidaTipo(@Argument Long id) {
        unidadMedidaTipoService.deleteUnidadMedidaTipo(id);
        return true;
    }
}
