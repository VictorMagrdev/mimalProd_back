package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.InventarioLoteRequest;
import com.example.minimal_prod_backend.dto.Response.*;
import com.example.minimal_prod_backend.service.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InventarioLoteController {

    private final InventarioLoteService inventarioLoteService;
    private final ProductoService productoService;
    private final LoteProduccionService loteProduccionService;
    private final BodegaService bodegaService;
    private final UnidadMedidaService unidadMedidaService;

    public InventarioLoteController(InventarioLoteService inventarioLoteService, ProductoService productoService, LoteProduccionService loteProduccionService, BodegaService bodegaService, UnidadMedidaService unidadMedidaService) {
        this.inventarioLoteService = inventarioLoteService;
        this.productoService = productoService;
        this.loteProduccionService = loteProduccionService;
        this.bodegaService = bodegaService;
        this.unidadMedidaService = unidadMedidaService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('INVENTARIOS_LOTE_TAG', 'READ')")
    public List<InventarioLoteResponse> inventarioLotes() {
        return inventarioLoteService.getInventarioLotes();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('INVENTARIOS_LOTE_TAG', 'READ')")
    public InventarioLoteResponse inventarioLote(@Argument Long id) {
        return inventarioLoteService.getInventarioLoteById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('INVENTARIOS_LOTE_TAG', 'CREATE')")
    public InventarioLoteResponse createInventarioLote(@Argument("input") InventarioLoteRequest inventarioLoteInput) {
        return inventarioLoteService.createInventarioLote(inventarioLoteInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('INVENTARIOS_LOTE_TAG', 'UPDATE')")
    public InventarioLoteResponse updateInventarioLote(@Argument Long id, @Argument("input") InventarioLoteRequest inventarioLoteInput) {
        return inventarioLoteService.updateInventarioLote(id, inventarioLoteInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('INVENTARIOS_LOTE_TAG', 'DELETE')")
    public boolean deleteInventarioLote(@Argument Long id) {
        inventarioLoteService.deleteInventarioLote(id);
        return true;
    }
    @SchemaMapping(typeName = "InventarioLote", field = "producto")
    public ProductoResponse producto(InventarioLoteResponse inventarioLote) {
        return productoService.getProductoById(inventarioLote.productoId());
    }
    @SchemaMapping(typeName = "InventarioLote", field = "lote")
    public LoteProduccionResponse lote(InventarioLoteResponse inventarioLote) {
        return loteProduccionService.getLoteProduccionById(inventarioLote.loteId());
    }
    @SchemaMapping(typeName = "InventarioLote", field = "bodega")
    public BodegaResponse bodega(InventarioLoteResponse inventarioLote) {
        return bodegaService.getBodegaById(inventarioLote.bodegaId());
    }
    @SchemaMapping(typeName = "InventarioLote", field = "unidad")
    public UnidadMedidaResponse unidad(InventarioLoteResponse inventarioLote) {
        return unidadMedidaService.getUnidadMedidaById(inventarioLote.unidadId());
    }
}
