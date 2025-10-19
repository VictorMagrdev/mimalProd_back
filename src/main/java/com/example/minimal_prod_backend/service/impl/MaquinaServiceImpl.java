package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.MaquinaRequest;
import com.example.minimal_prod_backend.dto.MaquinaResponse;
import com.example.minimal_prod_backend.entity.CentroCosto;
import com.example.minimal_prod_backend.entity.Maquina;
import com.example.minimal_prod_backend.mapper.MaquinaMapper;
import com.example.minimal_prod_backend.repository.CentroCostoRepository;
import com.example.minimal_prod_backend.repository.MaquinaRepository;
import com.example.minimal_prod_backend.service.MaquinaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MaquinaServiceImpl implements MaquinaService {

    private final MaquinaRepository maquinaRepository;
    private final CentroCostoRepository centroCostoRepository;
    private final MaquinaMapper maquinaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MaquinaResponse> getMaquinas() {
        return maquinaRepository.findAll()
                .stream()
                .map(maquinaMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MaquinaResponse getMaquinaById(Long id) {
        Maquina maquina = maquinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Máquina no encontrada con ID: " + id));
        return maquinaMapper.toResponse(maquina);
    }

    @Override
    public MaquinaResponse createMaquina(MaquinaRequest request) {
        Maquina maquina = maquinaMapper.toEntity(request);

        if (request.centroCostoId() != null) {
            CentroCosto centroCosto = centroCostoRepository.findById(request.centroCostoId())
                    .orElseThrow(() -> new EntityNotFoundException("Centro de costo no encontrado con ID: " + request.centroCostoId()));
            maquina.setCentroCosto(centroCosto);
        }

        Maquina saved = maquinaRepository.save(maquina);
        return maquinaMapper.toResponse(saved);
    }

    @Override
    public MaquinaResponse updateMaquina(Long id, MaquinaRequest request) {
        Maquina maquina = maquinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Máquina no encontrada con ID: " + id));

        maquinaMapper.updateEntityFromRequest(request, maquina);

        if (request.centroCostoId() != null) {
            CentroCosto centroCosto = centroCostoRepository.findById(request.centroCostoId())
                    .orElseThrow(() -> new EntityNotFoundException("Centro de costo no encontrado con ID: " + request.centroCostoId()));
            maquina.setCentroCosto(centroCosto);
        }

        Maquina updated = maquinaRepository.save(maquina);
        return maquinaMapper.toResponse(updated);
    }

    @Override
    public void deleteMaquina(Long id) {
        if (!maquinaRepository.existsById(id)) {
            throw new EntityNotFoundException("Máquina no encontrada con ID: " + id);
        }
        maquinaRepository.deleteById(id);
    }
}
