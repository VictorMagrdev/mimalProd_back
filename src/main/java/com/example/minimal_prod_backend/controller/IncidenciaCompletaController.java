package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.Request.IncidenciaConArchivosRequest;
import com.example.minimal_prod_backend.dto.Response.IncidenciaResponse;
import com.example.minimal_prod_backend.service.impl.IncidenciaCompletaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostMapping(value = "/con-archivos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IncidenciaResponse crearIncidenciaConArchivos(
            @RequestPart("incidencia") String incidenciaJson,
            @RequestPart(value = "archivos", required = false) List<MultipartFile> archivos
    ) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        IncidenciaConArchivosRequest request = objectMapper.readValue(incidenciaJson, IncidenciaConArchivosRequest.class);

        return service.crearConArchivos(request.withArchivos(archivos));
    }
}
