package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.IncidenciaRequest;
import com.example.minimal_prod_backend.dto.IncidenciaResponse;
import com.example.minimal_prod_backend.service.IncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IncidenciaController {

    private final IncidenciaService service;

    @QueryMapping
    public List<IncidenciaResponse> incidencias() {
        return service.findAll();
    }

    @QueryMapping
    public IncidenciaResponse incidencia(@Argument Long id) {
        return service.findById(id);
    }

    @MutationMapping
    public IncidenciaResponse createIncidencia(@Argument IncidenciaRequest input) {
        return service.create(input);
    }

    @MutationMapping
    public Boolean deleteIncidencia(@Argument Long id) {
        service.delete(id);
        return true;
    }
}
