package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.OrdenProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.*;
import com.example.minimal_prod_backend.service.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class OrdenProduccionController {

    private final OrdenProduccionService ordenProduccionService;
    private final UnidadMedidaService unidadMedidaService;
    private final EstadoOrdenService estadoOrdenService;
    private final LineaOrdenService lineaOrdenService;
    private final CostoOrdenService costoOrdenService;

    public OrdenProduccionController(OrdenProduccionService ordenProduccionService, UnidadMedidaService unidadMedidaService, EstadoOrdenService estadoOrdenService, LineaOrdenService lineaOrdenService, CostoOrdenService costoOrdenService) {
        this.ordenProduccionService = ordenProduccionService;
        this.unidadMedidaService = unidadMedidaService;
        this.estadoOrdenService = estadoOrdenService;
        this.lineaOrdenService = lineaOrdenService;
        this.costoOrdenService = costoOrdenService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_PRODUCCION_TAG', 'READ')")
    public List<OrdenProduccionResponse> ordenesProduccion() {
        return ordenProduccionService.getOrdenesProduccion();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_PRODUCCION_TAG', 'READ')")
    public OrdenProduccionResponse ordenProduccion(@Argument Long id) {
        return ordenProduccionService.getOrdenProduccionById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_PRODUCCION_TAG', 'CREATE')")
    public OrdenProduccionResponse createOrdenProduccion(@Argument("input") OrdenProduccionRequest ordenProduccionRequest) {
        return ordenProduccionService.createOrdenProduccion(ordenProduccionRequest);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_PRODUCCION_TAG', 'UPDATE')")
    public OrdenProduccionResponse updateOrdenProduccion(@Argument Long id, @Argument("input") OrdenProduccionRequest ordenProduccionRequest) {
        return ordenProduccionService.updateOrdenProduccion(id, ordenProduccionRequest);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ORDENES_PRODUCCION_TAG', 'DELETE')")
    public boolean deleteOrdenProduccion(@Argument Long id) {
        ordenProduccionService.deleteOrdenProduccion(id);
        return true;
    }

    @SchemaMapping(typeName = "OrdenProduccion", field = "unidad")
    public Optional<UnidadMedidaResponse> unidad(OrdenProduccionResponse orden) {
        return unidadMedidaService.findById(orden.unidadId());
    }
    @SchemaMapping(typeName = "OrdenProduccion", field = "estado")
    public Optional<EstadoOrdenResponse> estados(OrdenProduccionResponse orden) {
        return estadoOrdenService.findById(orden.estadoId());
    }
    @SchemaMapping(typeName = "OrdenProduccion", field = "lineas")
    public List<LineaOrdenResponse> lineas(OrdenProduccionResponse orden) {
        return lineaOrdenService.findByOrdenProduccion(orden.id());
    }
    @SchemaMapping(typeName = "OrdenProduccion", field = "costos")
    public List<CostoOrdenResponse> costos(OrdenProduccionResponse orden) {
        return costoOrdenService.findByOrdenProduccion(orden.id());
    }
}
