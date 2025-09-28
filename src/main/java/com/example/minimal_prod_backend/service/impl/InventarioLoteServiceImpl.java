package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.InventarioLoteInput;
import com.example.minimal_prod_backend.dto.InventarioLoteResponse;
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
        if (dto.getProductoId() != null) {
            Producto producto = productoRepository.findById(dto.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getProductoId()));
            entity.setProducto(producto);
        }

        if (dto.getLoteId() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getLoteId())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.getLoteId()));
            entity.setLote(lote);
        }

        if (dto.getBodegaId() != null) {
            Bodega bodega = bodegaRepository.findById(dto.getBodegaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + dto.getBodegaId()));
            entity.setBodega(bodega);
        }

        if (dto.getUnidadId() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getUnidadId())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getUnidadId()));
            entity.setUnidad(unidad);
        }
    }
}
