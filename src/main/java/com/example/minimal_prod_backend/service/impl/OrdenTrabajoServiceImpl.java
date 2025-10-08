package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.controller.graphql.OrdenTrabajoController;
import com.example.minimal_prod_backend.dto.OrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.OrdenTrabajoResponse;
import com.example.minimal_prod_backend.entity.OrdenTrabajo;
import com.example.minimal_prod_backend.mapper.OrdenTrabajoMapper;
import com.example.minimal_prod_backend.repository.OrdenTrabajoRepository;
import com.example.minimal_prod_backend.service.OrdenTrabajoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenTrabajoServiceImpl implements OrdenTrabajoService {
    private static final Logger logger = LoggerFactory.getLogger(OrdenTrabajoServiceImpl.class);
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
        logger.debug("2");
        OrdenTrabajo ordenTrabajo = ordenTrabajoMapper.toEntity(ordenTrabajoRequest);
        logger.debug("3");
        OrdenTrabajo saved = ordenTrabajoRepository.save(ordenTrabajo);
        logger.debug("4");
        return ordenTrabajoMapper.toResponse(saved);
    }

    @Override
    public void deleteById(Long id) {
        ordenTrabajoRepository.deleteById(id);
    }
}
