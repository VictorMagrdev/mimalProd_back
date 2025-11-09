package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.EstadoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoIncidenciaResponse;
import com.example.minimal_prod_backend.service.EstadoIncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EstadoIncidenciaController {

    private final EstadoIncidenciaService service;

    @QueryMapping
    public List<EstadoIncidenciaResponse> estadosIncidencia() {
        return service.findAll();
    }

    @QueryMapping
    public EstadoIncidenciaResponse estadoIncidencia(@Argument Long id) {
        return service.findById(id);
    }

    @MutationMapping
    public EstadoIncidenciaResponse createEstadoIncidencia(@Argument EstadoIncidenciaRequest input) {
        return service.create(input);
    }

    @MutationMapping
    public Boolean deleteEstadoIncidencia(@Argument Long id) {
        service.delete(id);
        return true;
    }
}
