package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.InventarioLoteMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.InventarioLoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioLoteServiceImpl implements InventarioLoteService {

    private final InventarioLoteRepository inventarioLoteRepository;
    private final ProductoRepository productoRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final BodegaRepository bodegaRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final InventarioLoteMapper mapper;

    @Override
    public List<InventarioLoteResponse> getInventarioLotes() {
        return inventarioLoteRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public InventarioLoteResponse getInventarioLoteById(Long id) {
        InventarioLote entity = inventarioLoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InventarioLote not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    public InventarioLoteResponse createInventarioLote(InventarioLoteInput input) {
        InventarioLote entity = mapper.toEntity(input);
        attachRelations(input, entity);
        entity.setActualizadoEn(LocalDateTime.now());
        return mapper.toResponse(inventarioLoteRepository.save(entity));
    }

    @Override
    public InventarioLoteResponse updateInventarioLote(Long id, InventarioLoteInput input) {
        InventarioLote entity = inventarioLoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InventarioLote not found with id: " + id));
        mapper.updateEntityFromInput(input, entity);
        attachRelations(input, entity);
        entity.setActualizadoEn(LocalDateTime.now());
        return mapper.toResponse(inventarioLoteRepository.save(entity));
    }

    @Override
    public void deleteInventarioLote(Long id) {
        inventarioLoteRepository.deleteById(id);
    }

    /**
     * Resolver relaciones de IDs a entidades persistidas.
     */
    private void attachRelations(InventarioLoteInput dto, InventarioLote entity) {
        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        }

        if (dto.getIdLote() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getIdLote())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.getIdLote()));
            entity.setLote(lote);
        }

        if (dto.getIdBodega() != null) {
            Bodega bodega = bodegaRepository.findById(dto.getIdBodega())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + dto.getIdBodega()));
            entity.setBodega(bodega);
        }

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }
    }
}
