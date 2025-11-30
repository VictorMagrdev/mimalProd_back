package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.ConteoCiclicoRequest;
import com.example.minimal_prod_backend.dto.Response.ConteoCiclicoResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.ConteoCiclicoMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.ConteoCiclicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConteoCiclicoServiceImpl implements ConteoCiclicoService {

    private final ConteoCiclicoRepository conteoCiclicoRepository;
    private final ProductoRepository productoRepository;
    private final BodegaRepository bodegaRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final ConteoCiclicoMapper mapper;

    @Override
    public List<ConteoCiclicoResponse> getConteosCiclicos() {
        return mapper.toResponseList(conteoCiclicoRepository.findAll());
    }

    @Override
    public ConteoCiclicoResponse getConteoCiclicoById(Long id) {
        ConteoCiclico conteoCiclico = conteoCiclicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConteoCiclico not found with id: " + id));
        return mapper.toResponse(conteoCiclico);
    }

    @Override
    public ConteoCiclicoResponse createConteoCiclico(ConteoCiclicoRequest input) {
        ConteoCiclico entity = mapper.toEntity(input);

        attachRelations(input, entity);

        ConteoCiclico saved = conteoCiclicoRepository.save(entity);

        return mapper.toResponse(saved);
    }

    @Override
    public ConteoCiclicoResponse updateConteoCiclico(Long id, ConteoCiclicoRequest input) {
        ConteoCiclico existing = conteoCiclicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConteoCiclico not found with id: " + id));

        mapper.updateEntityFromInput(input, existing);

        attachRelations(input, existing);

        ConteoCiclico saved = conteoCiclicoRepository.save(existing);

        return mapper.toResponse(saved);
    }

    @Override
    public boolean deleteConteoCiclico(Long id) {
        if (!conteoCiclicoRepository.existsById(id)) {
            return false;
        }
        conteoCiclicoRepository.deleteById(id);
        return true;
    }

    private void attachRelations(ConteoCiclicoRequest dto, ConteoCiclico entity) {
        if (dto.productoId() != null) {
            entity.setProducto(productoRepository.getReferenceById(dto.productoId()));
        } else {
            entity.setProducto(null);
        }

        if (dto.bodegaId() != null) {
            entity.setBodega(bodegaRepository.getReferenceById(dto.bodegaId()));
        } else {
            entity.setBodega(null);
        }

        if (dto.loteId() != null) {
            entity.setLote(loteProduccionRepository.getReferenceById(dto.loteId()));
        } else {
            entity.setLote(null);
        }

        if (dto.unidadId() != null) {
            entity.setUnidad(unidadMedidaRepository.getReferenceById(dto.unidadId()));
        } else {
            entity.setUnidad(null);
        }
    }

}
