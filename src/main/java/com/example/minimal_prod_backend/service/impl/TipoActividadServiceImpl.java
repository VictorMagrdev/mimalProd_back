package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.TipoActividadRequest;
import com.example.minimal_prod_backend.dto.Response.TipoActividadResponse;
import com.example.minimal_prod_backend.entity.TipoActividad;
import com.example.minimal_prod_backend.mapper.TipoActividadMapper;
import com.example.minimal_prod_backend.repository.TipoActividadRepository;
import com.example.minimal_prod_backend.service.TipoActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoActividadServiceImpl implements TipoActividadService {

    @Autowired
    private TipoActividadRepository tipoActividadRepository;

    @Autowired
    private TipoActividadMapper tipoActividadMapper;

    @Override
    public List<TipoActividadResponse> findAll() {
        return tipoActividadRepository.findAll().stream()
                .map(tipoActividadMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TipoActividadResponse findById(Long id) {
        TipoActividad tipoActividad = tipoActividadRepository.findById(id).orElse(null);
        return tipoActividadMapper.toResponse(tipoActividad);
    }

    @Override
    public TipoActividadResponse save(TipoActividadRequest tipoActividadRequest) {
        TipoActividad tipoActividad = tipoActividadMapper.toEntity(tipoActividadRequest);
        return tipoActividadMapper.toResponse(tipoActividadRepository.save(tipoActividad));
    }

    @Override
    public void deleteById(Long id) {
        tipoActividadRepository.deleteById(id);
    }
}
