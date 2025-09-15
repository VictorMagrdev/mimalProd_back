package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.InventarioLoteInput;
import com.example.minimal_prod_backend.dto.InventarioLoteResponse;
import com.example.minimal_prod_backend.service.InventarioLoteService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InventarioLoteController {

    private final InventarioLoteService inventarioLoteService;

    public InventarioLoteController(InventarioLoteService inventarioLoteService) {
        this.inventarioLoteService = inventarioLoteService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('INVENTARIO_LOTE_TAG', 'READ')")
    public List<InventarioLoteResponse> inventarioLotes() {
        return inventarioLoteService.getInventarioLotes();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('INVENTARIO_LOTE_TAG', 'READ')")
    public InventarioLoteResponse inventarioLote(@Argument Long id) {
        return inventarioLoteService.getInventarioLoteById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('INVENTARIO_LOTE_TAG', 'CREATE')")
    public InventarioLoteResponse createInventarioLote(@Argument("input") InventarioLoteInput inventarioLoteInput) {
        return inventarioLoteService.createInventarioLote(inventarioLoteInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('INVENTARIO_LOTE_TAG', 'UPDATE')")
    public InventarioLoteResponse updateInventarioLote(@Argument Long id, @Argument("input") InventarioLoteInput inventarioLoteInput) {
        return inventarioLoteService.updateInventarioLote(id, inventarioLoteInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('INVENTARIO_LOTE_TAG', 'DELETE')")
    public boolean deleteInventarioLote(@Argument Long id) {
        inventarioLoteService.deleteInventarioLote(id);
        return true;
    }
}
