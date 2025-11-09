package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.LineaOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.LineaOrdenResponse;
import com.example.minimal_prod_backend.service.LineaOrdenService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LineaOrdenController {

    private final LineaOrdenService lineaOrdenService;

    public LineaOrdenController(LineaOrdenService lineaOrdenService) {
        this.lineaOrdenService = lineaOrdenService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('LINEAS_ORDEN_TAG', 'READ')")
    public List<LineaOrdenResponse> lineasOrden() {
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
}
