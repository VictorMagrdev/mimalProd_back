package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.OrdenProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenProduccionServiceImpl implements OrdenProduccionService {

    private final OrdenProduccionRepository ordenProduccionRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final ProductoRepository productoRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final EstadoOrdenRepository estadoOrdenRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenProduccionResponse> getOrdenesProduccion() {
        return ordenProduccionRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrdenProduccionResponse getOrdenProduccionById(Integer id) {
        OrdenProduccion ordenProduccion = ordenProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + id));
        return toResponse(ordenProduccion);
    }

    @Override
    @Transactional
    public OrdenProduccionResponse createOrdenProduccion(OrdenProduccionInput ordenProduccionInput) {
        OrdenProduccion ordenProduccion = toEntity(ordenProduccionInput);
        return toResponse(ordenProduccionRepository.save(ordenProduccion));
    }

    @Override
    @Transactional
    public OrdenProduccionResponse updateOrdenProduccion(Integer id, OrdenProduccionInput ordenProduccionInput) {
        OrdenProduccion existingOrdenProduccion = ordenProduccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + id));
        updateEntityFromInput(ordenProduccionInput, existingOrdenProduccion);
        return toResponse(ordenProduccionRepository.save(existingOrdenProduccion));
    }

    @Override
    public void deleteOrdenProduccion(Integer id) {
        ordenProduccionRepository.deleteById(id);
    }

    private OrdenProduccionResponse toResponse(OrdenProduccion entity) {
        if (entity == null) return null;
        OrdenProduccionResponse dto = new OrdenProduccionResponse();
        dto.setId(entity.getId());
        dto.setNumeroOrden(entity.getNumeroOrden());
        dto.setCantidad(entity.getCantidad());
        dto.setInicioPlanificado(entity.getInicioPlanificado());
        dto.setFinPlanificado(entity.getFinPlanificado());
        dto.setInicioReal(entity.getInicioReal());
        dto.setFinReal(entity.getFinReal());
        dto.setCantidadDesperdicio(entity.getCantidadDesperdicio());
        dto.setCantidadProducida(entity.getCantidadProducida());
        dto.setCreadoPor(entity.getCreadoPor());
        dto.setObservaciones(entity.getObservaciones());
        dto.setCreadoEn(entity.getCreadoEn());
        dto.setActualizadoEn(entity.getActualizadoEn());

        if (entity.getLote() != null) {
            LoteProduccionResponse loteDto = new LoteProduccionResponse();
            loteDto.setId(entity.getLote().getId());
            loteDto.setNumeroLote(entity.getLote().getNumeroLote());
            dto.setLote(loteDto);
        }

        if (entity.getProducto() != null) {
            ProductoResponse productoDto = new ProductoResponse();
            productoDto.setId(entity.getProducto().getId());
            productoDto.setCodigo(entity.getProducto().getCodigo());
            productoDto.setNombre(entity.getProducto().getNombre());
            dto.setProducto(productoDto);
        }

        if (entity.getUnidad() != null) {
            UnidadMedidaResponse unidadDto = new UnidadMedidaResponse();
            unidadDto.setId(entity.getUnidad().getId());
            unidadDto.setNombre(entity.getUnidad().getNombre());
            unidadDto.setAbreviatura(entity.getUnidad().getAbreviatura());
            dto.setUnidad(unidadDto);
        }

        if (entity.getEstado() != null) {
            EstadoOrdenResponse estadoDto = new EstadoOrdenResponse();
            estadoDto.setId(entity.getEstado().getId());
            estadoDto.setCodigo(entity.getEstado().getCodigo());
            estadoDto.setNombre(entity.getEstado().getNombre());
            dto.setEstado(estadoDto);
        }

        return dto;
    }

    private OrdenProduccion toEntity(OrdenProduccionInput dto) {
        if (dto == null) return null;
        OrdenProduccion entity = new OrdenProduccion();
        updateEntityFromInput(dto, entity);
        return entity;
    }

    private void updateEntityFromInput(OrdenProduccionInput dto, OrdenProduccion entity) {
        if (dto == null || entity == null) return;

        entity.setNumeroOrden(dto.getNumeroOrden());
        entity.setCantidad(dto.getCantidad());
        entity.setInicioPlanificado(dto.getInicioPlanificado());
        entity.setFinPlanificado(dto.getFinPlanificado());
        entity.setInicioReal(dto.getInicioReal());
        entity.setFinReal(dto.getFinReal());
        entity.setCantidadDesperdicio(dto.getCantidadDesperdicio());
        entity.setCantidadProducida(dto.getCantidadProducida());
        entity.setCreadoPor(dto.getCreadoPor());
        entity.setObservaciones(dto.getObservaciones());

        if (dto.getIdLote() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getIdLote()).orElseThrow(() -> new ResourceNotFoundException("Lote not found"));
            entity.setLote(lote);
        }
        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(Math.toIntExact(dto.getIdProducto())).orElseThrow(() -> new ResourceNotFoundException("Producto not found"));
            entity.setProducto(producto);
        }
        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad()).orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found"));
            entity.setUnidad(unidad);
        }
        if (dto.getIdEstado() != null) {
            EstadoOrden estado = estadoOrdenRepository.findById(dto.getIdEstado()).orElseThrow(() -> new ResourceNotFoundException("EstadoOrden not found"));
            entity.setEstado(estado);
        }
    }
}
