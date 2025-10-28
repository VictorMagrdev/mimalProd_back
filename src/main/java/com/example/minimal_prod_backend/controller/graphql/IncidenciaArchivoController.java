package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.IncidenciaArchivoRequest;
import com.example.minimal_prod_backend.dto.IncidenciaArchivoResponse;
import com.example.minimal_prod_backend.service.IncidenciaArchivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IncidenciaArchivoController {

    private final IncidenciaArchivoService service;

    @QueryMapping
    public List<IncidenciaArchivoResponse> incidenciasArchivo() {
        return service.findAll();
    }

    @QueryMapping
    public List<IncidenciaArchivoResponse> incidenciasArchivos(@Argument Long incidenciaId) {
        return service.findByIncidencia(incidenciaId);
    }

    @MutationMapping
    public IncidenciaArchivoResponse createIncidenciaArchivo(@Argument IncidenciaArchivoRequest input) {
        return service.create(input);
    }

    @MutationMapping
    public Boolean deleteIncidenciaArchivo(@Argument Long id) {
        service.delete(id);
        return true;
    }
}
