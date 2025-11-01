package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioRequest;
import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioResponse;
import com.example.minimal_prod_backend.entity.ConteoCiclico;
import com.example.minimal_prod_backend.entity.DiscrepanciaInventario;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.DiscrepanciaInventarioMapper;
import com.example.minimal_prod_backend.repository.ConteoCiclicoRepository;
import com.example.minimal_prod_backend.repository.DiscrepanciaInventarioRepository;
import com.example.minimal_prod_backend.service.DiscrepanciaInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscrepanciaInventarioServiceImpl implements DiscrepanciaInventarioService {

    private final DiscrepanciaInventarioRepository discrepanciaInventarioRepository;
    private final ConteoCiclicoRepository conteoCiclicoRepository;
    private final DiscrepanciaInventarioMapper discrepanciaInventarioMapper;

    @Override
    public List<DiscrepanciaInventarioResponse> getDiscrepanciasInventario() {
        return discrepanciaInventarioMapper.toResponseList(discrepanciaInventarioRepository.findAll());
    }

    @Override
    public DiscrepanciaInventarioResponse getDiscrepanciaInventarioById(Long id) {
        DiscrepanciaInventario discrepanciaInventario = discrepanciaInventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DiscrepanciaInventario not found with id: " + id));
        return discrepanciaInventarioMapper.toResponse(discrepanciaInventario);
    }

    @Override
    public DiscrepanciaInventarioResponse createDiscrepanciaInventario(DiscrepanciaInventarioRequest dto) {
        if (dto.cantidadSistema() == null) {
            throw new IllegalArgumentException("cantidadSistema es requerida");
        }

        DiscrepanciaInventario entity = discrepanciaInventarioMapper.toEntity(dto);

        if (entity.getResuelto() == null) {
            entity.setResuelto(false);
        }

        if (dto.conteoId() != null) {
            ConteoCiclico conteo = conteoCiclicoRepository.findById(dto.conteoId())
                    .orElseThrow(() -> new ResourceNotFoundException("ConteoCiclico not found with id: " + dto.conteoId()));
            entity.setConteo(conteo);
        }

        return discrepanciaInventarioMapper.toResponse(discrepanciaInventarioRepository.save(entity));
    }

    @Override
    public DiscrepanciaInventarioResponse updateDiscrepanciaInventario(Long id, DiscrepanciaInventarioRequest dto) {
        DiscrepanciaInventario existing = discrepanciaInventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DiscrepanciaInventario not found with id: " + id));

        discrepanciaInventarioMapper.updateEntityFromInput(dto, existing);

        if (dto.conteoId() != null) {
            ConteoCiclico conteo = conteoCiclicoRepository.findById(dto.conteoId())
                    .orElseThrow(() -> new ResourceNotFoundException("ConteoCiclico not found with id: " + dto.conteoId()));
            existing.setConteo(conteo);
        }

        return discrepanciaInventarioMapper.toResponse(discrepanciaInventarioRepository.save(existing));
    }

    @Override
    public void deleteDiscrepanciaInventario(Long id) {
        if (!discrepanciaInventarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("DiscrepanciaInventario not found with id: " + id);
        }
        discrepanciaInventarioRepository.deleteById(id);
    }
}
