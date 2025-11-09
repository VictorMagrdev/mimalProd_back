package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.RutaProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.RutaProduccionResponse;
import com.example.minimal_prod_backend.service.RutaProduccionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RutaProduccionController {

    private final RutaProduccionService rutaProduccionService;

    public RutaProduccionController(RutaProduccionService rutaProduccionService) {
        this.rutaProduccionService = rutaProduccionService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('RUTAS_PRODUCCION_TAG', 'READ')")
    public List<RutaProduccionResponse> rutasProduccion() {
        return rutaProduccionService.findAll();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('RUTAS_PRODUCCION_TAG', 'READ')")
    public RutaProduccionResponse rutaProduccion(@Argument Long id) {
        return rutaProduccionService.findById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('RUTAS_PRODUCCION_TAG', 'CREATE')")
    public RutaProduccionResponse createRutaProduccion(@Argument("input") RutaProduccionRequest rutaProduccionInput) {
        return rutaProduccionService.save(rutaProduccionInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('RUTAS_PRODUCCION_TAG', 'DELETE')")
    public boolean deleteRutaProduccion(@Argument Long id) {
        rutaProduccionService.deleteById(id);
        return true;
    }
}