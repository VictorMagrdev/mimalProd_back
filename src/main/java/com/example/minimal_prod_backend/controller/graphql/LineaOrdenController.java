package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.LineaOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.*;
import com.example.minimal_prod_backend.service.LineaOrdenService;
import com.example.minimal_prod_backend.service.ProductoService;
import com.example.minimal_prod_backend.service.UnidadMedidaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LineaOrdenController {

    private final LineaOrdenService lineaOrdenService;
    private final ProductoService productoService;
    private final UnidadMedidaService unidadMedidaService;

    public LineaOrdenController(LineaOrdenService lineaOrdenService, ProductoService productoService, UnidadMedidaService unidadMedidaService) {
        this.lineaOrdenService = lineaOrdenService;
        this.productoService = productoService;
        this.unidadMedidaService = unidadMedidaService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('LINEAS_ORDEN_TAG', 'READ')")
    public List<LineaOrdenResponse> LineasOrden() {
        return lineaOrdenService.getLineasOrden();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('LINEAS_ORDEN_TAG', 'READ')")
    public LineaOrdenResponse lineaOrden(@Argument Long id) {
        return lineaOrdenService.getLineaOrdenById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('LINEAS_ORDEN_TAG', 'CREATE')")
    public LineaOrdenResponse createLineaOrden(@Argument("input") LineaOrdenRequest LineaOrdenRequest) {
        return lineaOrdenService.createLineaOrden(LineaOrdenRequest);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('LINEAS_ORDEN_TAG', 'UPDATE')")
    public LineaOrdenResponse updateLineaOrden(@Argument Long id, @Argument("input") LineaOrdenRequest LineaOrdenRequest) {
        return lineaOrdenService.updateLineaOrden(id, LineaOrdenRequest);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('LINEAS_ORDEN_TAG', 'DELETE')")
    public boolean deleteLineaOrden(@Argument Long id) {
        lineaOrdenService.deleteLineaOrden(id);
        return true;
    }
    @SchemaMapping(typeName = "LineaOrden", field = "productoComponente")
    public ProductoResponse productoComponente(LineaOrdenResponse lineaOrden) {
        return productoService.getProductoById(lineaOrden.productoComponenteId());
    }
    @SchemaMapping(typeName = "LineaOrden", field = "unidadComponente")
    public UnidadMedidaResponse unidadComponente(LineaOrdenResponse lineaOrden) {
        return unidadMedidaService.getUnidadMedidaById(lineaOrden.unidadComponenteId());
    }
}
