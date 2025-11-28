package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.UnidadConversionRequest;
import com.example.minimal_prod_backend.dto.Response.IncidenciaArchivoResponse;
import com.example.minimal_prod_backend.dto.Response.IncidenciaResponse;
import com.example.minimal_prod_backend.dto.Response.UnidadConversionResponse;
import com.example.minimal_prod_backend.dto.Response.UnidadMedidaResponse;
import com.example.minimal_prod_backend.service.UnidadConversionService;
import com.example.minimal_prod_backend.service.UnidadMedidaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UnidadConversionController {

    private final UnidadConversionService unidadConversionService;
    private final UnidadMedidaService unidadMedidaService;
    public UnidadConversionController(UnidadConversionService unidadConversionService, UnidadMedidaService unidadMedidaService) {
        this.unidadConversionService = unidadConversionService;
        this.unidadMedidaService = unidadMedidaService;
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
    public UnidadConversionResponse createUnidadConversion(@Argument("input") UnidadConversionRequest unidadConversionInput) {
        return unidadConversionService.createUnidadConversion(unidadConversionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_CONVERSION_TAG', 'UPDATE')")
    public UnidadConversionResponse updateUnidadConversion(@Argument Long id, @Argument("input") UnidadConversionRequest unidadConversionInput) {
        return unidadConversionService.updateUnidadConversion(id, unidadConversionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('UNIDADES_CONVERSION_TAG', 'DELETE')")
    public boolean deleteUnidadConversion(@Argument Long id) {
        unidadConversionService.deleteUnidadConversion(id);
        return true;
    }

    @SchemaMapping(typeName = "UnidadConversion", field = "origen")
    public UnidadMedidaResponse origen(UnidadConversionResponse unidadConversionRequest) {
        return unidadMedidaService.getUnidadMedidaById(unidadConversionRequest.origenId());
    }

    @SchemaMapping(typeName = "UnidadConversion", field = "destino")
    public UnidadMedidaResponse destino(UnidadConversionResponse unidadConversionRequest) {
        return unidadMedidaService.getUnidadMedidaById(unidadConversionRequest.destinoId());
    }
}
