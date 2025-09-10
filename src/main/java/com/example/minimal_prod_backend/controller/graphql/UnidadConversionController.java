package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.entity.UnidadConversion;
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
    @PreAuthorize("@customSecurity.hasPermission('UNIDAD_CONVERSION_TAG', 'READ')")
    public List<UnidadConversion> unidadesConversion() {
        return unidadConversionService.getUnidadConversiones();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDAD_CONVERSION_TAG', 'READ')")
    public UnidadConversion unidadConversion(@Argument Long id) {
        return unidadConversionService.getUnidadConversionById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDAD_CONVERSION_TAG', 'CREATE')")
    public UnidadConversion createUnidadConversion(@Argument UnidadConversion unidadConversion) {
        return unidadConversionService.createUnidadConversion(unidadConversion);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDAD_CONVERSION_TAG', 'UPDATE')")
    public UnidadConversion updateUnidadConversion(@Argument Long id, @Argument UnidadConversion unidadConversion) {
        return unidadConversionService.updateUnidadConversion(id, unidadConversion);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDAD_CONVERSION_TAG', 'DELETE')")
    public boolean deleteUnidadConversion(@Argument Long id) {
        unidadConversionService.deleteUnidadConversion(id);
        return true;
    }
}
