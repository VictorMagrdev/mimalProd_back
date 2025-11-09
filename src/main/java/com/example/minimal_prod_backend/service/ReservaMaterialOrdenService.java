package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.Request.ReservaMaterialOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.ReservaMaterialOrdenResponse;

import java.util.List;

public interface ReservaMaterialOrdenService {
    List<ReservaMaterialOrdenResponse> getReservasMaterialOrden();

    ReservaMaterialOrdenResponse getReservaMaterialOrdenById(Long id);

    ReservaMaterialOrdenResponse createReservaMaterialOrden(ReservaMaterialOrdenRequest reservaMaterialOrdenInput);

    ReservaMaterialOrdenResponse updateReservaMaterialOrden(Long id, ReservaMaterialOrdenRequest reservaMaterialOrdenInput);

    void deleteReservaMaterialOrden(Long id);
}
