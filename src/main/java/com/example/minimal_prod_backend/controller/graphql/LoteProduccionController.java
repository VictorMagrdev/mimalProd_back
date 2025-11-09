package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.LoteProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.LoteProduccionResponse;
import com.example.minimal_prod_backend.service.LoteProduccionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LoteProduccionController {

    private final LoteProduccionService loteProduccionService;

    public LoteProduccionController(LoteProduccionService loteProduccionService) {
        this.loteProduccionService = loteProduccionService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('LOTES_PRODUCCION_TAG', 'READ')")
    public List<LoteProduccionResponse> lotesProduccion() {
        return loteProduccionService.getLotesProduccion();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('LOTES_PRODUCCION_TAG', 'READ')")
    public LoteProduccionResponse loteProduccion(@Argument Long id) {
        return loteProduccionService.getLoteProduccionById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('LOTES_PRODUCCION_TAG', 'CREATE')")
    public LoteProduccionResponse createLoteProduccion(@Argument("input") LoteProduccionRequest loteProduccionInput) {
        return loteProduccionService.createLoteProduccion(loteProduccionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('LOTES_PRODUCCION_TAG', 'UPDATE')")
    public LoteProduccionResponse updateLoteProduccion(@Argument Long id, @Argument("input") LoteProduccionRequest loteProduccionInput) {
        return loteProduccionService.updateLoteProduccion(id, loteProduccionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('LOTES_PRODUCCION_TAG', 'DELETE')")
    public boolean deleteLoteProduccion(@Argument Long id) {
        loteProduccionService.deleteLoteProduccion(id);
        return true;
    }
}
