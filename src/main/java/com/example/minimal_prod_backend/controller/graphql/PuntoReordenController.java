package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.PuntoReordenRequest;
import com.example.minimal_prod_backend.dto.PuntoReordenResponse;
import com.example.minimal_prod_backend.service.PuntoReordenService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PuntoReordenController {

    private final PuntoReordenService puntoReordenService;

    public PuntoReordenController(PuntoReordenService puntoReordenService) {
        this.puntoReordenService = puntoReordenService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('PUNTOS_REORDEN_TAG', 'READ')")
    public List<PuntoReordenResponse> puntosReorden() {
        return puntoReordenService.getPuntosReorden();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('PUNTOS_REORDEN_TAG', 'READ')")
    public PuntoReordenResponse puntoReorden(@Argument Long id) {
        return puntoReordenService.getPuntoReordenById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('PUNTOS_REORDEN_TAG', 'CREATE')")
    public PuntoReordenResponse createPuntoReorden(@Argument("input") PuntoReordenRequest puntoReordenInput) {
        return puntoReordenService.createPuntoReorden(puntoReordenInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('PUNTOS_REORDEN_TAG', 'UPDATE')")
    public PuntoReordenResponse updatePuntoReorden(@Argument Long id, @Argument("input") PuntoReordenRequest puntoReordenInput) {
        return puntoReordenService.updatePuntoReorden(id, puntoReordenInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('PUNTOS_REORDEN_TAG', 'DELETE')")
    public boolean deletePuntoReorden(@Argument Long id) {
        puntoReordenService.deletePuntoReorden(id);
        return true;
    }
}
