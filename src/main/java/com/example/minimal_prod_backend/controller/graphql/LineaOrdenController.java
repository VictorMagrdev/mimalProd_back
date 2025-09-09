package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.entity.LineaOrden;
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
    @PreAuthorize("hasPermission('LINEA_ORDEN', 'READ')")
    public List<LineaOrden> lineasOrden() {
        return lineaOrdenService.getLineasOrden();
    }

    @QueryMapping
    @PreAuthorize("hasPermission('LINEA_ORDEN', 'READ')")
    public LineaOrden lineaOrden(@Argument Long id) {
        return lineaOrdenService.getLineaOrdenById(id);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('LINEA_ORDEN', 'CREATE')")
    public LineaOrden createLineaOrden(@Argument LineaOrden lineaOrden) {
        return lineaOrdenService.createLineaOrden(lineaOrden);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('LINEA_ORDEN', 'UPDATE')")
    public LineaOrden updateLineaOrden(@Argument Long id, @Argument LineaOrden lineaOrden) {
        return lineaOrdenService.updateLineaOrden(id, lineaOrden);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('LINEA_ORDEN', 'DELETE')")
    public boolean deleteLineaOrden(@Argument Long id) {
        lineaOrdenService.deleteLineaOrden(id);
        return true;
    }
}
