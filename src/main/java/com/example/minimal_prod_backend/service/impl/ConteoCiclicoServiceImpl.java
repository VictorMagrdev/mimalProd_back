package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.ConteoCiclicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConteoCiclicoServiceImpl implements ConteoCiclicoService {

    private final ConteoCiclicoRepository conteoCiclicoRepository;
    private final ProductoRepository productoRepository;
    private final BodegaRepository bodegaRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<ConteoCiclicoResponse> getConteosCiclicos() {
        return conteoCiclicoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConteoCiclicoResponse getConteoCiclicoById(Long id) {
        ConteoCiclico conteoCiclico = conteoCiclicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConteoCiclico not found with id: " + id));
        return toResponse(conteoCiclico);
    }

    @Override
    public ConteoCiclicoResponse createConteoCiclico(ConteoCiclicoInput conteoCiclicoInput) {
        ConteoCiclico conteoCiclico = toEntity(conteoCiclicoInput);
        return toResponse(conteoCiclicoRepository.save(conteoCiclico));
    }

    @Override
    public ConteoCiclicoResponse updateConteoCiclico(Long id, ConteoCiclicoInput conteoCiclicoInput) {
        ConteoCiclico existingConteoCiclico = conteoCiclicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConteoCiclico not found with id: " + id));
        updateEntityFromInput(conteoCiclicoInput, existingConteoCiclico);
        return toResponse(conteoCiclicoRepository.save(existingConteoCiclico));
    }

    @Override
    public void deleteConteoCiclico(Long id) {
        conteoCiclicoRepository.deleteById(id);
    }

    private ConteoCiclicoResponse toResponse(ConteoCiclico entity) {
        if (entity == null) return null;
        ConteoCiclicoResponse dto = new ConteoCiclicoResponse();
        dto.setId(entity.getId());
        dto.setCantidadContada(entity.getCantidadContada());
        dto.setFecha(entity.getFecha());

        if (entity.getProducto() != null) {
            dto.setProducto(new ProductoResponse());
            dto.getProducto().setId(entity.getProducto().getId());
            dto.getProducto().setNombre(entity.getProducto().getNombre());
        }

        if (entity.getBodega() != null) {
            dto.setBodega(new BodegaResponse());
            dto.getBodega().setId(entity.getBodega().getId());
            dto.getBodega().setNombre(entity.getBodega().getNombre());
        }

        if (entity.getLote() != null) {
            dto.setLote(new LoteProduccionResponse());
            dto.getLote().setId(entity.getLote().getId());
            dto.getLote().setNumeroLote(entity.getLote().getNumeroLote());
        }

        if (entity.getUnidad() != null) {
            dto.setUnidad(new UnidadMedidaResponse());
            dto.getUnidad().setId(entity.getUnidad().getId());
            dto.getUnidad().setNombre(entity.getUnidad().getNombre());
        }

        return dto;
    }

    private ConteoCiclico toEntity(ConteoCiclicoInput dto) {
        if (dto == null) return null;
        ConteoCiclico entity = new ConteoCiclico();
        entity.setCantidadContada(dto.getCantidadContada());
        entity.setFecha(dto.getFecha());

        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        }

        if (dto.getIdBodega() != null) {
            Bodega bodega = bodegaRepository.findById(dto.getIdBodega())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + dto.getIdBodega()));
            entity.setBodega(bodega);
        }

        if (dto.getIdLote() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getIdLote())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.getIdLote()));
            entity.setLote(lote);
        }

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }

        return entity;
    }

    private void updateEntityFromInput(ConteoCiclicoInput dto, ConteoCiclico entity) {
        if (dto == null || entity == null) return;

        entity.setCantidadContada(dto.getCantidadContada());
        entity.setFecha(dto.getFecha());

        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        }

        if (dto.getIdBodega() != null) {
            Bodega bodega = bodegaRepository.findById(dto.getIdBodega())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + dto.getIdBodega()));
            entity.setBodega(bodega);
        }

        if (dto.getIdLote() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getIdLote())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.getIdLote()));
            entity.setLote(lote);
        }

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }
    }
}
