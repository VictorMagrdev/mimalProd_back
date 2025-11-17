package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.IncidenciaConArchivosRequest;
import com.example.minimal_prod_backend.dto.Response.IncidenciaResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.mapper.IncidenciaCompletaMapper;
import com.example.minimal_prod_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IncidenciaCompletaService {

    private final IncidenciaRepository incidenciaRepository;
    private final IncidenciaArchivoRepository archivoRepository;
    private final IncidenciaBuilderService builder;
    private final IncidenciaArchivoService archivoService;
    private final IncidenciaCompletaMapper mapper;

    public IncidenciaResponse crearConArchivos(IncidenciaConArchivosRequest req) throws IOException {

        Incidencia incidencia = builder.build(req);
        incidencia = incidenciaRepository.save(incidencia);

        List<IncidenciaArchivo> archivos =
                archivoService.procesar(req.archivos(), incidencia, req.reportadoPor());

        archivoRepository.saveAll(archivos);

        return mapper.toResponse(incidencia);
    }
}

