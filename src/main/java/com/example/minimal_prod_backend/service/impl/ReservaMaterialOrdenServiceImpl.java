package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.ReservaMaterialOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.ReservaMaterialOrdenResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.ReservaMaterialOrdenMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.ReservaMaterialOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaMaterialOrdenServiceImpl implements ReservaMaterialOrdenService {

    private final ReservaMaterialOrdenRepository reservaMaterialOrdenRepository;
    private final ReservaMaterialOrdenMapper mapper;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final ProductoRepository productoRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<ReservaMaterialOrdenResponse> getReservasMaterialOrden() {
        return mapper.toResponseList(reservaMaterialOrdenRepository.findAll());
    }

    @Override
    public ReservaMaterialOrdenResponse getReservaMaterialOrdenById(Long id) {
        ReservaMaterialOrden reserva = reservaMaterialOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReservaMaterialOrden not found with id: " + id));
        return mapper.toResponse(reserva);
    }
    @Override
    public ReservaMaterialOrdenResponse createReservaMaterialOrden(ReservaMaterialOrdenRequest input) {
        ReservaMaterialOrden entity = mapper.toEntity(input);

        attachRelations(input, entity);

        ReservaMaterialOrden saved = reservaMaterialOrdenRepository.save(entity);
        return mapper.toResponse(saved);
    }


    @Override
    public ReservaMaterialOrdenResponse updateReservaMaterialOrden(Long id, ReservaMaterialOrdenRequest input) {
        ReservaMaterialOrden reserva = reservaMaterialOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReservaMaterialOrden not found with id: " + id));
        mapper.updateEntityFromInput(input, reserva);
        return mapper.toResponse(reservaMaterialOrdenRepository.save(reserva));
    }
    private void attachRelations(ReservaMaterialOrdenRequest dto, ReservaMaterialOrden entity) {

        if (dto.ordenId() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.ordenId())
                    .orElseThrow(() -> new ResourceNotFoundException("Orden not found: " + dto.ordenId()));
            entity.setOrden(orden);
        }

        if (dto.productoId() != null) {
            Producto producto = productoRepository.findById(dto.productoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found: " + dto.productoId()));
            entity.setProducto(producto);
        }

        if (dto.loteId() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.loteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lote not found: " + dto.loteId()));
            entity.setLote(lote);
        }

        if (dto.unidadId() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.unidadId())
                    .orElseThrow(() -> new ResourceNotFoundException("Unidad not found: " + dto.unidadId()));
            entity.setUnidad(unidad);
        }
    }

    @Override
    public void deleteReservaMaterialOrden(Long id) {
        reservaMaterialOrdenRepository.deleteById(id);
    }
}
