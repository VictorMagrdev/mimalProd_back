package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.LoteProduccionRepository;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.service.LoteProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoteProduccionServiceImpl implements LoteProduccionService {

    private final LoteProduccionRepository loteProduccionRepository;
    private final ProductoRepository productoRepository;
    private final LoteProduccionMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<LoteProduccionResponse> getLotesProduccion() {
        return loteProduccionRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LoteProduccionResponse getLoteProduccionById(Long id) {
        LoteProduccion entity = loteProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public LoteProduccionResponse createLoteProduccion(LoteProduccionInput input) {
        LoteProduccion entity = mapper.toEntity(input);
        attachRelations(input, entity);
        return mapper.toResponse(loteProduccionRepository.save(entity));
    }

    @Override
    @Transactional
    public LoteProduccionResponse updateLoteProduccion(Long id, LoteProduccionInput input) {
        LoteProduccion entity = loteProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + id));
        mapper.updateEntityFromInput(input, entity);
        attachRelations(input, entity);
        return mapper.toResponse(loteProduccionRepository.save(entity));
    }

    @Override
    public void deleteLoteProduccion(Long id) {
        loteProduccionRepository.deleteById(id);
    }

    private void attachRelations(LoteProduccionInput dto, LoteProduccion entity) {
        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        } else {
            entity.setProducto(null);
        }
    }
}
