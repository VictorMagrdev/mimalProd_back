package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.InventarioLoteRequest;
import com.example.minimal_prod_backend.dto.Response.InventarioLoteResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.InventarioLoteMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.InventarioLoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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
    public InventarioLoteResponse createInventarioLote(InventarioLoteRequest input) {
        InventarioLote entity = mapper.toEntity(input);
        attachRelations(input, entity);
        entity.setActualizadoEn(OffsetDateTime.now());
        return mapper.toResponse(inventarioLoteRepository.save(entity));
    }

    @Override
    public InventarioLoteResponse updateInventarioLote(Long id, InventarioLoteRequest input) {
        InventarioLote entity = inventarioLoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InventarioLote not found with id: " + id));
        mapper.updateEntityFromInput(input, entity);
        attachRelations(input, entity);
        entity.setActualizadoEn(OffsetDateTime.now());
        return mapper.toResponse(inventarioLoteRepository.save(entity));
    }

    @Override
    public void deleteInventarioLote(Long id) {
        inventarioLoteRepository.deleteById(id);
    }

    /**
     * Resolver relaciones de IDs a entidades persistidas.
     */
    private void attachRelations(InventarioLoteRequest dto, InventarioLote entity) {
        if (dto.productoId() != null) {
            Producto producto = productoRepository.findById(dto.productoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.productoId()));
            entity.setProducto(producto);
        }

        if (dto.loteId() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.loteId())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.loteId()));
            entity.setLote(lote);
        }

        if (dto.bodegaId() != null) {
            Bodega bodega = bodegaRepository.findById(dto.bodegaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + dto.bodegaId()));
            entity.setBodega(bodega);
        }

        if (dto.unidadId() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.unidadId())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.unidadId()));
            entity.setUnidad(unidad);
        }
    }
}
