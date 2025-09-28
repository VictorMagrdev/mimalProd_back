package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.ReservaMaterialOrdenInput;
import com.example.minimal_prod_backend.dto.ReservaMaterialOrdenResponse;
import com.example.minimal_prod_backend.entity.ReservaMaterialOrden;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.ReservaMaterialOrdenRepository;
import com.example.minimal_prod_backend.service.ReservaMaterialOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaMaterialOrdenServiceImpl implements ReservaMaterialOrdenService {

    private final ReservaMaterialOrdenRepository reservaMaterialOrdenRepository;
    private final ReservaMaterialOrdenMapper mapper;

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
    public ReservaMaterialOrdenResponse createReservaMaterialOrden(ReservaMaterialOrdenInput input) {
        ReservaMaterialOrden reserva = mapper.toEntity(input);
        return mapper.toResponse(reservaMaterialOrdenRepository.save(reserva));
    }

    @Override
    public ReservaMaterialOrdenResponse updateReservaMaterialOrden(Long id, ReservaMaterialOrdenInput input) {
        ReservaMaterialOrden reserva = reservaMaterialOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReservaMaterialOrden not found with id: " + id));
        mapper.updateEntityFromInput(input, reserva);
        return mapper.toResponse(reservaMaterialOrdenRepository.save(reserva));
    }

    @Override
    public void deleteReservaMaterialOrden(Long id) {
        reservaMaterialOrdenRepository.deleteById(id);
    }
}
