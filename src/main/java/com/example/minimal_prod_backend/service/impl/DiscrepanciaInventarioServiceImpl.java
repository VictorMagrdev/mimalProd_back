package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.ConteoCiclicoResponse;
import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioInput;
import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioResponse;
import com.example.minimal_prod_backend.entity.ConteoCiclico;
import com.example.minimal_prod_backend.entity.DiscrepanciaInventario;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.ConteoCiclicoRepository;
import com.example.minimal_prod_backend.repository.DiscrepanciaInventarioRepository;
import com.example.minimal_prod_backend.service.DiscrepanciaInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscrepanciaInventarioServiceImpl implements DiscrepanciaInventarioService {

    private final DiscrepanciaInventarioRepository discrepanciaInventarioRepository;
    private final ConteoCiclicoRepository conteoCiclicoRepository;

    @Override
    public List<DiscrepanciaInventarioResponse> getDiscrepanciasInventario() {
        return discrepanciaInventarioRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DiscrepanciaInventarioResponse getDiscrepanciaInventarioById(Long id) {
        DiscrepanciaInventario discrepanciaInventario = discrepanciaInventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DiscrepanciaInventario not found with id: " + id));
        return toResponse(discrepanciaInventario);
    }

    @Override
    public DiscrepanciaInventarioResponse createDiscrepanciaInventario(DiscrepanciaInventarioInput discrepanciaInventarioInput) {
        DiscrepanciaInventario discrepanciaInventario = toEntity(discrepanciaInventarioInput);
        return toResponse(discrepanciaInventarioRepository.save(discrepanciaInventario));
    }

    @Override
    public DiscrepanciaInventarioResponse updateDiscrepanciaInventario(Long id, DiscrepanciaInventarioInput discrepanciaInventarioInput) {
        DiscrepanciaInventario existingDiscrepanciaInventario = discrepanciaInventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DiscrepanciaInventario not found with id: " + id));
        updateEntityFromInput(discrepanciaInventarioInput, existingDiscrepanciaInventario);
        return toResponse(discrepanciaInventarioRepository.save(existingDiscrepanciaInventario));
    }

    @Override
    public void deleteDiscrepanciaInventario(Long id) {
        discrepanciaInventarioRepository.deleteById(id);
    }

    private DiscrepanciaInventarioResponse toResponse(DiscrepanciaInventario entity) {
        if (entity == null) return null;
        DiscrepanciaInventarioResponse dto = new DiscrepanciaInventarioResponse();
        dto.setId(entity.getId());
        dto.setCantidadSistema(entity.getCantidadSistema());
        dto.setCantidadContada(entity.getCantidadContada());
        dto.setDiferencia(entity.getDiferencia());
        dto.setResuelto(entity.getResuelto());

        if (entity.getConteo() != null) {
            dto.setConteo(new ConteoCiclicoResponse());
            dto.getConteo().setId(entity.getConteo().getId());
        }

        return dto;
    }

    private DiscrepanciaInventario toEntity(DiscrepanciaInventarioInput dto) {
        if (dto == null) return null;
        DiscrepanciaInventario entity = new DiscrepanciaInventario();
        entity.setCantidadSistema(dto.getCantidadSistema());
        entity.setCantidadContada(dto.getCantidadContada());
        entity.setResuelto(dto.getResuelto());

        if (dto.getIdConteo() != null) {
            ConteoCiclico conteo = conteoCiclicoRepository.findById(dto.getIdConteo())
                    .orElseThrow(() -> new ResourceNotFoundException("ConteoCiclico not found with id: " + dto.getIdConteo()));
            entity.setConteo(conteo);
        }

        return entity;
    }

    private void updateEntityFromInput(DiscrepanciaInventarioInput dto, DiscrepanciaInventario entity) {
        if (dto == null || entity == null) return;

        entity.setCantidadSistema(dto.getCantidadSistema());
        entity.setCantidadContada(dto.getCantidadContada());
        entity.setResuelto(dto.getResuelto());

        if (dto.getIdConteo() != null) {
            ConteoCiclico conteo = conteoCiclicoRepository.findById(dto.getIdConteo())
                    .orElseThrow(() -> new ResourceNotFoundException("ConteoCiclico not found with id: " + dto.getIdConteo()));
            entity.setConteo(conteo);
        }
    }
}
