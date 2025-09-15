package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.TipoBodegaInput;
import com.example.minimal_prod_backend.dto.TipoBodegaResponse;
import com.example.minimal_prod_backend.service.TipoBodegaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TipoBodegaController {

    private final TipoBodegaService tipoBodegaService;

    public TipoBodegaController(TipoBodegaService tipoBodegaService) {
        this.tipoBodegaService = tipoBodegaService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_BODEGA_TAG', 'READ')")
    public List<TipoBodegaResponse> tiposBodega() {
        return tipoBodegaService.getTiposBodega();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_BODEGA_TAG', 'READ')")
    public TipoBodegaResponse tipoBodega(@Argument Long id) {
        return tipoBodegaService.getTipoBodegaById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_BODEGA_TAG', 'CREATE')")
    public TipoBodegaResponse createTipoBodega(@Argument("input") TipoBodegaInput tipoBodegaInput) {
        return tipoBodegaService.createTipoBodega(tipoBodegaInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_BODEGA_TAG', 'UPDATE')")
    public TipoBodegaResponse updateTipoBodega(@Argument Long id, @Argument("input") TipoBodegaInput tipoBodegaInput) {
        return tipoBodegaService.updateTipoBodega(id, tipoBodegaInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TIPO_BODEGA_TAG', 'DELETE')")
    public boolean deleteTipoBodega(@Argument Long id) {
        tipoBodegaService.deleteTipoBodega(id);
        return true;
    }
}
