package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.DepreciacionRequest;
import com.example.minimal_prod_backend.dto.Response.DepreciacionResponse;
import com.example.minimal_prod_backend.dto.Response.IncidenciaArchivoResponse;
import com.example.minimal_prod_backend.entity.Depreciacion;
import com.example.minimal_prod_backend.entity.Maquina;
import com.example.minimal_prod_backend.entity.TipoPeriodo;
import com.example.minimal_prod_backend.mapper.DepreciacionMapper;
import com.example.minimal_prod_backend.repository.DepreciacionRepository;
import com.example.minimal_prod_backend.repository.MaquinaRepository;
import com.example.minimal_prod_backend.service.DepreciacionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
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

        depreciacionMapper.updateEntityFromInput(request, depreciacion);

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

    public void calcularDepreciacionMensual() {
        LocalDate periodo = LocalDate.now().withDayOfMonth(1);
        calcularDepreciacion(TipoPeriodo.MENSUAL, periodo);
    }

    public void calcularDepreciacionAnual() {
        LocalDate periodo = LocalDate.now().withDayOfYear(1);
        calcularDepreciacion(TipoPeriodo.ANUAL, periodo);
    }

    @Override
    public List<IncidenciaArchivoResponse> findByMaquina(Long id) {
        return depreciacionRepository.findByMaquina(id);
    }

    private void calcularDepreciacion(TipoPeriodo tipoPeriodo, LocalDate periodo) {
        // Verificar si ya se calculó la depreciación para este período
        if (depreciacionRepository.existsByTipoPeriodoAndPeriodo(tipoPeriodo, periodo)) {
            throw new RuntimeException("Ya se calculó la depreciación para el período: " + periodo);
        }

        // Obtener máquinas activas
        List<Maquina> maquinasActivas = maquinaRepository.findByActivaTrue();

        for (Maquina maquina : maquinasActivas) {
            calcularDepreciacionMaquina(maquina, tipoPeriodo, periodo);
        }
    }

    private void calcularDepreciacionMaquina(Maquina maquina, TipoPeriodo tipoPeriodo, LocalDate periodo) {
        // Obtener última depreciación calculada
        Depreciacion ultimaDepreciacion = depreciacionRepository
                .findTopByMaquinaIdOrderByPeriodoDesc(maquina.getId())
                .orElse(null);

        BigDecimal depreciacionPeriodo = calcularDepreciacionPeriodo(maquina, tipoPeriodo);
        BigDecimal depreciacionAcumulada = calcularDepreciacionAcumulada(ultimaDepreciacion, depreciacionPeriodo);
        BigDecimal valorNeto = calcularValorNeto(maquina, depreciacionAcumulada);

        Depreciacion nuevaDepreciacion = new Depreciacion();
        nuevaDepreciacion.setMaquina(maquina);
        nuevaDepreciacion.setTipoPeriodo(tipoPeriodo);
        nuevaDepreciacion.setPeriodo(periodo);
        nuevaDepreciacion.setDepreciacionPeriodo(depreciacionPeriodo);
        nuevaDepreciacion.setDepreciacionAcumulada(depreciacionAcumulada);
        nuevaDepreciacion.setValorNeto(valorNeto);

        depreciacionRepository.save(nuevaDepreciacion);
    }

    private BigDecimal calcularDepreciacionPeriodo(Maquina maquina, TipoPeriodo tipoPeriodo) {
        // Fórmula: (Costo - Valor Rescate) / Vida Útil
        BigDecimal baseDepreciable = maquina.getCostoCompra().subtract(maquina.getValorRescate());
        BigDecimal depreciacionAnual = baseDepreciable.divide(
                BigDecimal.valueOf(maquina.getVidaUtilAnios()), 2, RoundingMode.HALF_UP);

        if (tipoPeriodo == TipoPeriodo.MENSUAL) {
            return depreciacionAnual.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        } else {
            return depreciacionAnual;
        }
    }

    private BigDecimal calcularDepreciacionAcumulada(Depreciacion ultimaDepreciacion, BigDecimal depreciacionPeriodo) {
        if (ultimaDepreciacion == null) {
            return depreciacionPeriodo;
        }
        return ultimaDepreciacion.getDepreciacionAcumulada().add(depreciacionPeriodo);
    }

    private BigDecimal calcularValorNeto(Maquina maquina, BigDecimal depreciacionAcumulada) {
        return maquina.getCostoCompra().subtract(depreciacionAcumulada);
    }
}
