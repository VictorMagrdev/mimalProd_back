package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.IncidenciaRequest;
import com.example.minimal_prod_backend.dto.Response.IncidenciaArchivoResponse;
import com.example.minimal_prod_backend.dto.Response.IncidenciaResponse;
import com.example.minimal_prod_backend.service.*;
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
    private final TipoIncidenciaService tipoIncidenciaService;
    private final EstadoIncidenciaService estadoIncidenciaService;
    private final MaquinaService maquinaService;
    private final OrdenProduccionService ordenProduccionService;
    private final EstacionProduccionService estacionProduccionService;

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


    @SchemaMapping(typeName = "Incidencia", field = "tipoIncidencia")
    public Object tipoIncidencia(IncidenciaResponse incidencia) {
        return tipoIncidenciaService.findById(incidencia.tipoIncidenciaId());
    }

    @SchemaMapping(typeName = "Incidencia", field = "estado")
    public Object estado(IncidenciaResponse incidencia) {
        return estadoIncidenciaService.findById(incidencia.estadoId());
    }

    @SchemaMapping(typeName = "Incidencia", field = "maquina")
    public Object maquina(IncidenciaResponse incidencia) {
        return maquinaService.findById(incidencia.maquinaId());
    }

    @SchemaMapping(typeName = "Incidencia", field = "orden")
    public Object orden(IncidenciaResponse incidencia) {
        return ordenProduccionService.findById(incidencia.ordenId());
    }

    @SchemaMapping(typeName = "Incidencia", field = "estacion")
    public Object estacion(IncidenciaResponse incidencia) {
        return estacionProduccionService.findById(incidencia.estacionId());
    }

    @SchemaMapping(typeName = "Incidencia", field = "archivos")
    public List<IncidenciaArchivoResponse> archivos(IncidenciaResponse incidencia) {
        return incidenciaArchivoService.findByIncidencia(incidencia.id());
    }
}
