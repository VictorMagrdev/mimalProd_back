package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.ReservaMaterialOrdenInput;
import com.example.minimal_prod_backend.dto.ReservaMaterialOrdenResponse;

import java.util.List;

public interface ReservaMaterialOrdenService {
    List<ReservaMaterialOrdenResponse> getReservasMaterialOrden();
    ReservaMaterialOrdenResponse getReservaMaterialOrdenById(Long id);
    ReservaMaterialOrdenResponse createReservaMaterialOrden(ReservaMaterialOrdenInput reservaMaterialOrdenInput);
    ReservaMaterialOrdenResponse updateReservaMaterialOrden(Long id, ReservaMaterialOrdenInput reservaMaterialOrdenInput);
    void deleteReservaMaterialOrden(Long id);
}
