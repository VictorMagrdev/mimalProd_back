package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.UnidadMedidaTipoRequest;
import com.example.minimal_prod_backend.dto.Response.UnidadMedidaTipoResponse;
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
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_MEDIDA_TIPO_TAG', 'READ')")
    public List<UnidadMedidaTipoResponse> unidadesMedidaTipo() {
        return unidadMedidaTipoService.getUnidadMedidaTipos();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_MEDIDA_TIPO_TAG', 'READ')")
    public UnidadMedidaTipoResponse unidadMedidaTipo(@Argument Long id) {
        return unidadMedidaTipoService.getUnidadMedidaTipoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_MEDIDA_TIPO_TAG', 'CREATE')")
    public UnidadMedidaTipoResponse createUnidadMedidaTipo(@Argument("input") UnidadMedidaTipoRequest unidadMedidaTipoInput) {
        return unidadMedidaTipoService.createUnidadMedidaTipo(unidadMedidaTipoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_MEDIDA_TIPO_TAG', 'UPDATE')")
    public UnidadMedidaTipoResponse updateUnidadMedidaTipo(@Argument Long id, @Argument("input") UnidadMedidaTipoRequest unidadMedidaTipoInput) {
        return unidadMedidaTipoService.updateUnidadMedidaTipo(id, unidadMedidaTipoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_MEDIDA_TIPO_TAG', 'DELETE')")
    public boolean deleteUnidadMedidaTipo(@Argument Long id) {
        unidadMedidaTipoService.deleteUnidadMedidaTipo(id);
        return true;
    }
}
