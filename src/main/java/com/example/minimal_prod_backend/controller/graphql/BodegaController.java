package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.BodegaRequest;
import com.example.minimal_prod_backend.dto.BodegaResponse;
import com.example.minimal_prod_backend.dto.TipoBodegaResponse;
import com.example.minimal_prod_backend.service.BodegaService;
import com.example.minimal_prod_backend.service.TipoBodegaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BodegaController {

    private final BodegaService bodegaService;
    private final TipoBodegaService tipoBodegaService;

    public BodegaController(BodegaService bodegaService, TipoBodegaService tipoBodegaService) {
        this.bodegaService = bodegaService;
        this.tipoBodegaService = tipoBodegaService;
    }
    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('BODEGAS_TAG', 'READ')")
    public List<BodegaResponse> bodegas() {
        return bodegaService.getBodegas();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('BODEGAS_TAG', 'READ')")
    public BodegaResponse bodega(@Argument Long id) {
        return bodegaService.getBodegaById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('BODEGAS_TAG', 'CREATE')")
    public BodegaResponse createBodega(@Argument("input") BodegaRequest bodegaInput) {
        return bodegaService.createBodega(bodegaInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('BODEGAS_TAG', 'UPDATE')")
    public BodegaResponse updateBodega(@Argument Long id, @Argument("input") BodegaRequest bodegaInput) {
        return bodegaService.updateBodega(id, bodegaInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('BODEGAS_TAG', 'DELETE')")
    public boolean deleteBodega(@Argument Long id) {
        bodegaService.deleteBodega(id);
        return true;
    }

    @SchemaMapping(typeName = "Bodega", field = "tipo")
    public TipoBodegaResponse tipo(BodegaResponse bodegaResponse) {
        if (bodegaResponse.tipoBodegaId() == null) {
            return null;
        }
        return tipoBodegaService.getTipoBodegaById(bodegaResponse.tipoBodegaId());
    }
}
