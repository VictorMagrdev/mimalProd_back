package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.CostoOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.*;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.entity.TipoCosto;
import com.example.minimal_prod_backend.service.CostoOrdenService;
import com.example.minimal_prod_backend.service.OrdenProduccionService;
import com.example.minimal_prod_backend.service.TipoCostoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CostoOrdenController {

    private final CostoOrdenService costoOrdenService;
    private final TipoCostoService tipoCostoService;
    private final OrdenProduccionService ordenProduccionService;

    public CostoOrdenController(CostoOrdenService costoOrdenService, TipoCostoService tipoCostoService, OrdenProduccionService ordenProduccionService) {
        this.costoOrdenService = costoOrdenService;
        this.tipoCostoService = tipoCostoService;
        this.ordenProduccionService = ordenProduccionService;
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
    @SchemaMapping(typeName = "CostoOrden", field = "tipoCosto")
    public Optional<TipoCostoResponse> tipoCosto(CostoOrdenResponse costoOrden) {
        return tipoCostoService.findById(costoOrden.tipoCostoId());
    }

    @SchemaMapping(typeName = "CostoOrden", field = "orden")
    public Optional<OrdenProduccionResponse> orden(CostoOrdenResponse costoOrden) {
        return ordenProduccionService.findById(costoOrden.ordenId());
    }
}
