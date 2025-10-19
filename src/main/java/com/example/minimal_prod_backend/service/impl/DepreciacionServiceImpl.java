package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.DepreciacionRequest;
import com.example.minimal_prod_backend.dto.DepreciacionResponse;
import com.example.minimal_prod_backend.entity.Depreciacion;
import com.example.minimal_prod_backend.entity.Maquina;
import com.example.minimal_prod_backend.mapper.DepreciacionMapper;
import com.example.minimal_prod_backend.repository.DepreciacionRepository;
import com.example.minimal_prod_backend.repository.MaquinaRepository;
import com.example.minimal_prod_backend.service.DepreciacionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepreciacionServiceImpl implements DepreciacionService {

    private final DepreciacionRepository depreciacionRepository;
    private final MaquinaRepository maquinaRepository;
    private final DepreciacionMapper depreciacionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DepreciacionResponse> getDepreciaciones() {
        return depreciacionRepository.findAll()
                .stream()
                .map(depreciacionMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DepreciacionResponse getDepreciacionById(Long id) {
        Depreciacion depreciacion = depreciacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Depreciación no encontrada con ID: " + id));
        return depreciacionMapper.toResponse(depreciacion);
    }

    @Override
    public DepreciacionResponse createDepreciacion(DepreciacionRequest request) {
        Maquina maquina = maquinaRepository.findById(request.maquinaId())
                .orElseThrow(() -> new EntityNotFoundException("Máquina no encontrada con ID: " + request.maquinaId()));

        Depreciacion depreciacion = depreciacionMapper.toEntity(request);
        depreciacion.setMaquina(maquina);

        Depreciacion saved = depreciacionRepository.save(depreciacion);
        return depreciacionMapper.toResponse(saved);
    }

    @Override
    public DepreciacionResponse updateDepreciacion(Long id, DepreciacionRequest request) {
        Depreciacion depreciacion = depreciacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Depreciación no encontrada con ID: " + id));

        depreciacion.setTipoPeriodo(request.tipoPeriodo());
        depreciacion.setPeriodo(request.periodo());
        depreciacion.setDepreciacionPeriodo(request.depreciacionPeriodo());
        depreciacion.setDepreciacionAcumulada(request.depreciacionAcumulada());
        depreciacion.setValorNeto(request.valorNeto());

        if (request.maquinaId() != null) {
            Maquina maquina = maquinaRepository.findById(request.maquinaId())
                    .orElseThrow(() -> new EntityNotFoundException("Máquina no encontrada con ID: " + request.maquinaId()));
            depreciacion.setMaquina(maquina);
        }

        Depreciacion updated = depreciacionRepository.save(depreciacion);
        return depreciacionMapper.toResponse(updated);
    }

    @Override
    public void deleteDepreciacion(Long id) {
        if (!depreciacionRepository.existsById(id)) {
            throw new EntityNotFoundException("Depreciación no encontrada con ID: " + id);
        }
        depreciacionRepository.deleteById(id);
    }
}
