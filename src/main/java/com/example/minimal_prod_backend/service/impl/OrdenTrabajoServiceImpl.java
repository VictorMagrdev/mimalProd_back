package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.OrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.OrdenTrabajoResponse;
import com.example.minimal_prod_backend.entity.OrdenTrabajo;
import com.example.minimal_prod_backend.mapper.OrdenTrabajoMapper;
import com.example.minimal_prod_backend.repository.OrdenTrabajoRepository;
import com.example.minimal_prod_backend.service.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenTrabajoServiceImpl implements OrdenTrabajoService {

    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;

    @Autowired
    private OrdenTrabajoMapper ordenTrabajoMapper;

    @Override
    public List<OrdenTrabajoResponse> findAll() {
        return ordenTrabajoRepository.findAll().stream()
                .map(ordenTrabajoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrdenTrabajoResponse findById(Long id) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).orElse(null);
        return ordenTrabajoMapper.toResponse(ordenTrabajo);
    }

    @Override
    public OrdenTrabajoResponse save(OrdenTrabajoRequest ordenTrabajoRequest) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoMapper.toEntity(ordenTrabajoRequest);
        return ordenTrabajoMapper.toResponse(ordenTrabajoRepository.save(ordenTrabajo));
    }

    @Override
    public void deleteById(Long id) {
        ordenTrabajoRepository.deleteById(id);
    }
}
