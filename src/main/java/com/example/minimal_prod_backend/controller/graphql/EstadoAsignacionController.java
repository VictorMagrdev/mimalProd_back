package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.EstadoAsignacionRequest;
import com.example.minimal_prod_backend.dto.EstadoAsignacionResponse;
import com.example.minimal_prod_backend.service.EstadoAsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EstadoAsignacionController {

    private final EstadoAsignacionService estadoAsignacionService;

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADOS_ASIGNACION_TAG', 'READ')")
    public List<EstadoAsignacionResponse> estadosAsignacion() {
        return estadoAsignacionService.getEstadosAsignacion();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADOS_ASIGNACION_TAG', 'READ')")
    public EstadoAsignacionResponse estadoAsignacion(@Argument Long id) {
        return estadoAsignacionService.getEstadoAsignacionById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADOS_ASIGNACION_TAG', 'WRITE')")
    public EstadoAsignacionResponse createEstadoAsignacion(@Argument("input") EstadoAsignacionRequest request) {
        return estadoAsignacionService.createEstadoAsignacion(request);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADOS_ASIGNACION_TAG', 'WRITE')")
    public EstadoAsignacionResponse updateEstadoAsignacion(@Argument Long id, @Argument("input") EstadoAsignacionRequest request) {
        return estadoAsignacionService.updateEstadoAsignacion(id, request);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ESTADOS_ASIGNACION_TAG', 'DELETE')")
    public boolean deleteEstadoAsignacion(@Argument Long id) {
        estadoAsignacionService.deleteEstadoAsignacion(id);
        return true;
    }
}
