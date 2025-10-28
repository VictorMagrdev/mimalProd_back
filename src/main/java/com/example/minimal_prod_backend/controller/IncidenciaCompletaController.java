package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.IncidenciaConArchivosRequest;
import com.example.minimal_prod_backend.dto.IncidenciaResponse;
import com.example.minimal_prod_backend.service.impl.IncidenciaCompletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaCompletaController {
    private final IncidenciaCompletaService service;

    @Autowired
    public IncidenciaCompletaController(IncidenciaCompletaService service) {
        this.service = service;
    }

    @PostMapping(value = "/con-archivos", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public IncidenciaResponse crearIncidenciaConArchivos(
            @RequestPart("incidencia") IncidenciaConArchivosRequest incidencia,
            @RequestPart("archivos") List<MultipartFile> archivos
    ) throws Exception {
        return service.crearConArchivos(
                new IncidenciaConArchivosRequest(
                        incidencia.codigo(),
                        incidencia.titulo(),
                        incidencia.descripcion(),
                        incidencia.tipoIncidenciaId(),
                        incidencia.estadoId(),
                        incidencia.fechaCierre(),
                        incidencia.tiempoParada(),
                        archivos
                )
        );
    }

}
