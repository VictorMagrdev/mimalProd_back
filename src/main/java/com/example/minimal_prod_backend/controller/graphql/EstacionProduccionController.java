package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.EstacionProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.EstacionProduccionResponse;
import com.example.minimal_prod_backend.service.EstacionProduccionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EstacionProduccionController {

    private final EstacionProduccionService estacionProduccionService;

    public EstacionProduccionController(EstacionProduccionService estacionProduccionService) {
        this.estacionProduccionService = estacionProduccionService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTACIONES_PRODUCCION_TAG', 'READ')")
    public List<EstacionProduccionResponse> estacionesProduccion() {
        return estacionProduccionService.getEstacionesProduccion();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTACIONES_PRODUCCION_TAG', 'READ')")
    public EstacionProduccionResponse estacionProduccion(@Argument Long id) {
        return estacionProduccionService.getEstacionProduccionById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTACIONES_PRODUCCION_TAG', 'CREATE')")
    public EstacionProduccionResponse createEstacionProduccion(@Argument("input") EstacionProduccionRequest estacionProduccionInput) {
        return estacionProduccionService.createEstacionProduccion(estacionProduccionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTACIONES_PRODUCCION_TAG', 'UPDATE')")
    public EstacionProduccionResponse updateEstacionProduccion(@Argument Long id, @Argument("input") EstacionProduccionRequest estacionProduccionInput) {
        return estacionProduccionService.updateEstacionProduccion(id, estacionProduccionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTACIONES_PRODUCCION_TAG', 'DELETE')")
    public boolean deleteEstacionProduccion(@Argument Long id) {
        estacionProduccionService.deleteEstacionProduccion(id);
        return true;
    }
}
