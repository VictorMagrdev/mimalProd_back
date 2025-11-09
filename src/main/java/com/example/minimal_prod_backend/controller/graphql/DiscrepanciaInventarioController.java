package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.DiscrepanciaInventarioRequest;
import com.example.minimal_prod_backend.dto.Response.DiscrepanciaInventarioResponse;
import com.example.minimal_prod_backend.service.DiscrepanciaInventarioService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DiscrepanciaInventarioController {

    private final DiscrepanciaInventarioService discrepanciaInventarioService;

    public DiscrepanciaInventarioController(DiscrepanciaInventarioService discrepanciaInventarioService) {
        this.discrepanciaInventarioService = discrepanciaInventarioService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('DISCREPANCIAS_INVENTARIO_TAG', 'READ')")
    public List<DiscrepanciaInventarioResponse> discrepanciasInventario() {
        return discrepanciaInventarioService.getDiscrepanciasInventario();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('DISCREPANCIAS_INVENTARIO_TAG', 'READ')")
    public DiscrepanciaInventarioResponse discrepanciaInventario(@Argument Long id) {
        return discrepanciaInventarioService.getDiscrepanciaInventarioById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('DISCREPANCIAS_INVENTARIO_TAG', 'CREATE')")
    public DiscrepanciaInventarioResponse createDiscrepanciaInventario(@Argument("input") DiscrepanciaInventarioRequest discrepanciaInventarioInput) {
        return discrepanciaInventarioService.createDiscrepanciaInventario(discrepanciaInventarioInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('DISCREPANCIAS_INVENTARIO_TAG', 'UPDATE')")
    public DiscrepanciaInventarioResponse updateDiscrepanciaInventario(@Argument Long id, @Argument("input") DiscrepanciaInventarioRequest discrepanciaInventarioInput) {
        return discrepanciaInventarioService.updateDiscrepanciaInventario(id, discrepanciaInventarioInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('DISCREPANCIAS_INVENTARIO_TAG', 'DELETE')")
    public boolean deleteDiscrepanciaInventario(@Argument Long id) {
        discrepanciaInventarioService.deleteDiscrepanciaInventario(id);
        return true;
    }
}
