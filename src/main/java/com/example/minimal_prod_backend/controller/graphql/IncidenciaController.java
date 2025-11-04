package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.service.IncidenciaArchivoService;
import com.example.minimal_prod_backend.service.IncidenciaService;
import com.example.minimal_prod_backend.service.TipoBodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IncidenciaController {

    private final IncidenciaService service;
    private final IncidenciaArchivoService incidenciaArchivoService;
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

    @SchemaMapping(typeName = "IncidenciaArchivo", field = "tipo")
    public List<IncidenciaArchivoResponse> archivo(IncidenciaResponse incidenciaResponse) {
        return incidenciaArchivoService.findByIncidencia(incidenciaResponse.id());
    }
}
