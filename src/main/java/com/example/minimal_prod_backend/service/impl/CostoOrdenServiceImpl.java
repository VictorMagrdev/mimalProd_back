package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.CostoOrdenInput;
import com.example.minimal_prod_backend.dto.CostoOrdenResponse;
import com.example.minimal_prod_backend.dto.OrdenProduccionResponse;
import com.example.minimal_prod_backend.dto.TipoCostoResponse;
import com.example.minimal_prod_backend.entity.CostoOrden;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.entity.TipoCosto;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.CostoOrdenRepository;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.repository.TipoCostoRepository;
import com.example.minimal_prod_backend.service.CostoOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CostoOrdenServiceImpl implements CostoOrdenService {

    private final CostoOrdenRepository costoOrdenRepository;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final TipoCostoRepository tipoCostoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CostoOrdenResponse> getCostosOrden() {
        return costoOrdenRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CostoOrdenResponse getCostoOrdenById(Integer id) {
        CostoOrden costoOrden = costoOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CostoOrden not found with id: " + id));
        return toResponse(costoOrden);
    }

    @Override
    @Transactional
    public CostoOrdenResponse createCostoOrden(CostoOrdenInput costoOrdenInput) {
        CostoOrden costoOrden = toEntity(costoOrdenInput);
        return toResponse(costoOrdenRepository.save(costoOrden));
    }

    @Override
    @Transactional
    public CostoOrdenResponse updateCostoOrden(Integer id, CostoOrdenInput costoOrdenInput) {
        CostoOrden existingCostoOrden = costoOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CostoOrden not found with id: " + id));
        updateEntityFromInput(costoOrdenInput, existingCostoOrden);
        return toResponse(costoOrdenRepository.save(existingCostoOrden));
    }

    @Override
    public void deleteCostoOrden(Integer id) {
        costoOrdenRepository.deleteById(id);
    }

    private CostoOrdenResponse toResponse(CostoOrden entity) {
        if (entity == null) return null;
        CostoOrdenResponse dto = new CostoOrdenResponse();
        dto.setId(entity.getId());
        dto.setDescripcion(entity.getDescripcion());
        dto.setMonto(entity.getMonto());
        dto.setMoneda(entity.getMoneda());
        dto.setRegistradoEn(entity.getRegistradoEn());

        if (entity.getOrden() != null) {
            OrdenProduccionResponse ordenDto = new OrdenProduccionResponse();
            ordenDto.setId(entity.getOrden().getId());
            ordenDto.setNumeroOrden(entity.getOrden().getNumeroOrden());
            dto.setOrden(ordenDto);
        }

        if (entity.getTipoCosto() != null) {
            TipoCostoResponse tipoDto = new TipoCostoResponse();
            tipoDto.setId(entity.getTipoCosto().getId());
            tipoDto.setCodigo(entity.getTipoCosto().getCodigo());
            tipoDto.setNombre(entity.getTipoCosto().getNombre());
            dto.setTipoCosto(tipoDto);
        }

        return dto;
    }

    private CostoOrden toEntity(CostoOrdenInput dto) {
        if (dto == null) return null;
        CostoOrden entity = new CostoOrden();
        updateEntityFromInput(dto, entity);
        return entity;
    }

    private void updateEntityFromInput(CostoOrdenInput dto, CostoOrden entity) {
        if (dto == null || entity == null) return;

        entity.setDescripcion(dto.getDescripcion());
        entity.setMonto(dto.getMonto());
        entity.setMoneda(dto.getMoneda());

        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden()).orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found"));
            entity.setOrden(orden);
        }
        if (dto.getIdTipoCosto() != null) {
            TipoCosto tipoCosto = tipoCostoRepository.findById(dto.getIdTipoCosto()).orElseThrow(() -> new ResourceNotFoundException("TipoCosto not found"));
            entity.setTipoCosto(tipoCosto);
        }
    }
}
