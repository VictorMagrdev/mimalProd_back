package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.EstadoAprobacionRequest;
import com.example.minimal_prod_backend.dto.EstadoAprobacionResponse;
import com.example.minimal_prod_backend.service.EstadoAprobacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EstadoAprobacionController {

    private final EstadoAprobacionService estadoAprobacionService;

    @QueryMapping
    public List<EstadoAprobacionResponse> estadosAprobacion() {
        return estadoAprobacionService.getEstadosAprobacion();
    }

    @QueryMapping
    public EstadoAprobacionResponse estadoAprobacion(@Argument Long id) {
        return estadoAprobacionService.getEstadoAprobacionById(id);
    }

    @MutationMapping
    public EstadoAprobacionResponse createEstadoAprobacion(@Argument("input") EstadoAprobacionRequest request) {
        return estadoAprobacionService.createEstadoAprobacion(request);
    }

    @MutationMapping
    public EstadoAprobacionResponse updateEstadoAprobacion(@Argument Long id, @Argument("input") EstadoAprobacionRequest request) {
        return estadoAprobacionService.updateEstadoAprobacion(id, request);
    }

    @MutationMapping
    public boolean deleteEstadoAprobacion(@Argument Long id) {
        estadoAprobacionService.deleteEstadoAprobacion(id);
        return true;
    }
}
