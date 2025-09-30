package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.UnidadConversionInput;
import com.example.minimal_prod_backend.dto.UnidadConversionResponse;
import com.example.minimal_prod_backend.service.UnidadConversionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UnidadConversionController {

    private final UnidadConversionService unidadConversionService;

    public UnidadConversionController(UnidadConversionService unidadConversionService) {
        this.unidadConversionService = unidadConversionService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_CONVERSION_TAG', 'READ')")
    public List<UnidadConversionResponse> unidadesConversion() {
        return unidadConversionService.getUnidadConversiones();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_CONVERSION_TAG', 'READ')")
    public UnidadConversionResponse unidadConversion(@Argument Long id) {
        return unidadConversionService.getUnidadConversionById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_CONVERSION_TAG', 'CREATE')")
    public UnidadConversionResponse createUnidadConversion(@Argument("input") UnidadConversionInput unidadConversionInput) {
        return unidadConversionService.createUnidadConversion(unidadConversionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_CONVERSION_TAG', 'UPDATE')")
    public UnidadConversionResponse updateUnidadConversion(@Argument Long id, @Argument("input") UnidadConversionInput unidadConversionInput) {
        return unidadConversionService.updateUnidadConversion(id, unidadConversionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_CONVERSION_TAG', 'DELETE')")
    public boolean deleteUnidadConversion(@Argument Long id) {
        unidadConversionService.deleteUnidadConversion(id);
        return true;
    }
}
