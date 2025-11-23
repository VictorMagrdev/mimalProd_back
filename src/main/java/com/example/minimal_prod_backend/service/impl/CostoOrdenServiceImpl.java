package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.CostoOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.CostoOrdenResponse;
import com.example.minimal_prod_backend.entity.CostoOrden;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.entity.TipoCosto;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.CostoOrdenMapper;
import com.example.minimal_prod_backend.repository.CostoOrdenRepository;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.repository.TipoCostoRepository;
import com.example.minimal_prod_backend.service.CostoOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostoOrdenServiceImpl implements CostoOrdenService {

    private final CostoOrdenRepository costoOrdenRepository;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final TipoCostoRepository tipoCostoRepository;
    private final CostoOrdenMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<CostoOrdenResponse> getCostosOrden() {
        return mapper.toResponseList(costoOrdenRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public CostoOrdenResponse getCostoOrdenById(Long id) {
        CostoOrden costoOrden = costoOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CostoOrden not found with id: " + id));
        return mapper.toResponse(costoOrden);
    }

    @Override
    @Transactional
    public CostoOrdenResponse createCostoOrden(CostoOrdenRequest costoOrdenInput) {
        CostoOrden costoOrden = mapper.toEntity(costoOrdenInput);
        attachRelations(costoOrdenInput, costoOrden);
        return mapper.toResponse(costoOrdenRepository.save(costoOrden));
    }

    @Override
    @Transactional
    public CostoOrdenResponse updateCostoOrden(Long id, CostoOrdenRequest costoOrdenInput) {
        CostoOrden existingCostoOrden = costoOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CostoOrden not found with id: " + id));
        mapper.updateEntityFromInput(costoOrdenInput, existingCostoOrden);
        attachRelations(costoOrdenInput, existingCostoOrden);
        return mapper.toResponse(costoOrdenRepository.save(existingCostoOrden));
    }

    @Override
    public void deleteCostoOrden(Long id) {
        costoOrdenRepository.deleteById(id);
    }

    @Override
    public List<CostoOrdenResponse> findByOrdenProduccion(Long id) {
        return costoOrdenRepository.findByOrden_Id(id);
    }

    private void attachRelations(CostoOrdenRequest dto, CostoOrden entity) {
        if (dto.ordenId() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.ordenId())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + dto.ordenId()));
            entity.setOrden(orden);
        }
        if (dto.tipoCostoId() != null) {
            TipoCosto tipoCosto = tipoCostoRepository.findById(dto.tipoCostoId())
                    .orElseThrow(() -> new ResourceNotFoundException("TipoCosto not found with id: " + dto.tipoCostoId()));
            entity.setTipoCosto(tipoCosto);
        }
    }
}
