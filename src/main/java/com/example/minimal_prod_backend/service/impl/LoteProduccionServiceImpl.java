package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.LoteProduccionInput;
import com.example.minimal_prod_backend.dto.LoteProduccionResponse;
import com.example.minimal_prod_backend.dto.ProductoResponse;
import com.example.minimal_prod_backend.entity.LoteProduccion;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.LoteProduccionRepository;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.service.LoteProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoteProduccionServiceImpl implements LoteProduccionService {

    private final LoteProduccionRepository loteProduccionRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<LoteProduccionResponse> getLotesProduccion() {
        return loteProduccionRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LoteProduccionResponse getLoteProduccionById(Integer id) {
        LoteProduccion loteProduccion = loteProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + id));
        return toResponse(loteProduccion);
    }

    @Override
    @Transactional
    public LoteProduccionResponse createLoteProduccion(LoteProduccionInput loteProduccionInput) {
        LoteProduccion loteProduccion = toEntity(loteProduccionInput);
        return toResponse(loteProduccionRepository.save(loteProduccion));
    }

    @Override
    @Transactional
    public LoteProduccionResponse updateLoteProduccion(Integer id, LoteProduccionInput loteProduccionInput) {
        LoteProduccion existingLoteProduccion = loteProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + id));
        updateEntityFromInput(loteProduccionInput, existingLoteProduccion);
        return toResponse(loteProduccionRepository.save(existingLoteProduccion));
    }

    @Override
    public void deleteLoteProduccion(Integer id) {
        loteProduccionRepository.deleteById(id);
    }

    private LoteProduccionResponse toResponse(LoteProduccion entity) {
        if (entity == null) return null;
        LoteProduccionResponse dto = new LoteProduccionResponse();
        dto.setId(entity.getId());
        dto.setNumeroLote(entity.getNumeroLote());
        dto.setFabricadoEn(entity.getFabricadoEn());
        dto.setVenceEn(entity.getVenceEn());
        dto.setCreadoEn(entity.getCreadoEn());

        if (entity.getProducto() != null) {
            ProductoResponse productoDto = new ProductoResponse();
            productoDto.setId(entity.getProducto().getId());
            productoDto.setCodigo(entity.getProducto().getCodigo());
            productoDto.setNombre(entity.getProducto().getNombre());
            dto.setProducto(productoDto);
        }

        return dto;
    }

    private LoteProduccion toEntity(LoteProduccionInput dto) {
        if (dto == null) return null;
        LoteProduccion entity = new LoteProduccion();
        updateEntityFromInput(dto, entity);
        return entity;
    }

    private void updateEntityFromInput(LoteProduccionInput dto, LoteProduccion entity) {
        if (dto == null || entity == null) return;

        entity.setNumeroLote(dto.getNumeroLote());
        entity.setFabricadoEn(dto.getFabricadoEn());
        entity.setVenceEn(dto.getVenceEn());

        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        } else {
            entity.setProducto(null);
        }
    }
}
