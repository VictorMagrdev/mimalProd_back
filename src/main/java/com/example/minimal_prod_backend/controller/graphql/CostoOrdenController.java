package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.CostoOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.CostoOrdenResponse;
import com.example.minimal_prod_backend.service.CostoOrdenService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CostoOrdenController {

    private final CostoOrdenService costoOrdenService;

    public CostoOrdenController(CostoOrdenService costoOrdenService) {
        this.costoOrdenService = costoOrdenService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('COSTOS_ORDEN_TAG', 'READ')")
    public List<CostoOrdenResponse> costosOrden() {
        return costoOrdenService.getCostosOrden();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('COSTOS_ORDEN_TAG', 'READ')")
    public CostoOrdenResponse costoOrden(@Argument Long id) {
        return costoOrdenService.getCostoOrdenById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('COSTOS_ORDEN_TAG', 'CREATE')")
    public CostoOrdenResponse createCostoOrden(@Argument("input") CostoOrdenRequest costoOrdenInput) {
        return costoOrdenService.createCostoOrden(costoOrdenInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('COSTOS_ORDEN_TAG', 'UPDATE')")
    public CostoOrdenResponse updateCostoOrden(@Argument Long id, @Argument("input") CostoOrdenRequest costoOrdenInput) {
        return costoOrdenService.updateCostoOrden(id, costoOrdenInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('COSTOS_ORDEN_TAG', 'DELETE')")
    public boolean deleteCostoOrden(@Argument Long id) {
        costoOrdenService.deleteCostoOrden(id);
        return true;
    }
}
