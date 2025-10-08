package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.EstadoOrdenEstacionRequest;
import com.example.minimal_prod_backend.dto.EstadoOrdenEstacionResponse;
import com.example.minimal_prod_backend.service.EstadoOrdenEstacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EstadoOrdenEstacionController {

    private final EstadoOrdenEstacionService estadoOrdenEstacionService;

    @QueryMapping
    public List<EstadoOrdenEstacionResponse> estadoOrdenEstaciones() {
        return estadoOrdenEstacionService.findAll();
    }

    @QueryMapping
    public EstadoOrdenEstacionResponse estadoOrdenEstacionById(@Argument Long id) {
        return estadoOrdenEstacionService.findById(id);
    }

    @MutationMapping
    public EstadoOrdenEstacionResponse createEstadoOrdenEstacion(@Argument EstadoOrdenEstacionRequest input) {
        return estadoOrdenEstacionService.save(input);
    }

    @MutationMapping
    public Boolean deleteEstadoOrdenEstacion(@Argument Long id) {
        estadoOrdenEstacionService.deleteById(id);
        return true;
    }
}
