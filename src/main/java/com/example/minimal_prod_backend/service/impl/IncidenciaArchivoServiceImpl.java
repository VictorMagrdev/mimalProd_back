package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.IncidenciaArchivoRequest;
import com.example.minimal_prod_backend.dto.Response.IncidenciaArchivoResponse;
import com.example.minimal_prod_backend.entity.IncidenciaArchivo;
import com.example.minimal_prod_backend.entity.Usuario;
import com.example.minimal_prod_backend.mapper.IncidenciaArchivoMapper;
import com.example.minimal_prod_backend.repository.IncidenciaArchivoRepository;
import com.example.minimal_prod_backend.security.CustomUserDetailsService;
import com.example.minimal_prod_backend.service.IncidenciaArchivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IncidenciaArchivoServiceImpl implements IncidenciaArchivoService {

    private final IncidenciaArchivoRepository repository;
    private final IncidenciaArchivoMapper mapper;
    private final CustomUserDetailsService userDetail;

    @Override
    public IncidenciaArchivoResponse create(IncidenciaArchivoRequest request) {
        IncidenciaArchivo entity = mapper.toEntity(request);
        Usuario currentUser = userDetail.getCurrentUser();
        entity.setSubidoPor(currentUser);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IncidenciaArchivoResponse> findByIncidencia(Long incidenciaId) {
        return repository.findByIncidenciaId(incidenciaId).stream()
                .map(mapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<IncidenciaArchivoResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }
}
