package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.ReservaMaterialOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaMaterialOrdenServiceImpl implements ReservaMaterialOrdenService {

    private final ReservaMaterialOrdenRepository reservaMaterialOrdenRepository;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final ProductoRepository productoRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<ReservaMaterialOrdenResponse> getReservasMaterialOrden() {
        return reservaMaterialOrdenRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaMaterialOrdenResponse getReservaMaterialOrdenById(Long id) {
        ReservaMaterialOrden reservaMaterialOrden = reservaMaterialOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReservaMaterialOrden not found with id: " + id));
        return toResponse(reservaMaterialOrden);
    }

    @Override
    public ReservaMaterialOrdenResponse createReservaMaterialOrden(ReservaMaterialOrdenInput reservaMaterialOrdenInput) {
        ReservaMaterialOrden reservaMaterialOrden = toEntity(reservaMaterialOrdenInput);
        return toResponse(reservaMaterialOrdenRepository.save(reservaMaterialOrden));
    }

    @Override
    public ReservaMaterialOrdenResponse updateReservaMaterialOrden(Long id, ReservaMaterialOrdenInput reservaMaterialOrdenInput) {
        ReservaMaterialOrden existingReservaMaterialOrden = reservaMaterialOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReservaMaterialOrden not found with id: " + id));
        updateEntityFromInput(reservaMaterialOrdenInput, existingReservaMaterialOrden);
        return toResponse(reservaMaterialOrdenRepository.save(existingReservaMaterialOrden));
    }

    @Override
    public void deleteReservaMaterialOrden(Long id) {
        reservaMaterialOrdenRepository.deleteById(id);
    }

    private ReservaMaterialOrdenResponse toResponse(ReservaMaterialOrden entity) {
        if (entity == null) return null;
        ReservaMaterialOrdenResponse dto = new ReservaMaterialOrdenResponse();
        dto.setId(entity.getId());
        dto.setCantidadReservada(entity.getCantidadReservada());
        dto.setFechaReserva(entity.getFechaReserva());

        if (entity.getOrden() != null) {
            dto.setOrden(new OrdenProduccionResponse());
            dto.getOrden().setId(entity.getOrden().getId());
        }

        if (entity.getProducto() != null) {
            dto.setProducto(new ProductoResponse());
            dto.getProducto().setId(entity.getProducto().getId());
            dto.getProducto().setNombre(entity.getProducto().getNombre());
        }

        if (entity.getLote() != null) {
            dto.setLote(new LoteProduccionResponse());
            dto.getLote().setId(entity.getLote().getId());
            dto.getLote().setNumeroLote(entity.getLote().getNumeroLote());
        }

        if (entity.getUnidad() != null) {
            dto.setUnidad(new UnidadMedidaResponse());
            dto.getUnidad().setId(entity.getUnidad().getId());
            dto.getUnidad().setNombre(entity.getUnidad().getNombre());
        }

        return dto;
    }

    private ReservaMaterialOrden toEntity(ReservaMaterialOrdenInput dto) {
        if (dto == null) return null;
        ReservaMaterialOrden entity = new ReservaMaterialOrden();
        entity.setCantidadReservada(dto.getCantidadReservada());
        entity.setFechaReserva(dto.getFechaReserva());

        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + dto.getIdOrden()));
            entity.setOrden(orden);
        }

        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        }

        if (dto.getIdLote() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getIdLote())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.getIdLote()));
            entity.setLote(lote);
        }

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }

        return entity;
    }

    private void updateEntityFromInput(ReservaMaterialOrdenInput dto, ReservaMaterialOrden entity) {
        if (dto == null || entity == null) return;

        entity.setCantidadReservada(dto.getCantidadReservada());
        entity.setFechaReserva(dto.getFechaReserva());

        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + dto.getIdOrden()));
            entity.setOrden(orden);
        }

        if (dto.getIdProducto() != null) {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getIdProducto()));
            entity.setProducto(producto);
        }

        if (dto.getIdLote() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getIdLote())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.getIdLote()));
            entity.setLote(lote);
        }

        if (dto.getIdUnidad() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidad()));
            entity.setUnidad(unidad);
        }
    }
}
