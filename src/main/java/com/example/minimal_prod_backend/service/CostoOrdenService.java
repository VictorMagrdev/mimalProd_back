package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.CostoOrden;

import java.util.List;

public interface CostoOrdenService {
    List<CostoOrden> getCostosOrden();
    CostoOrden getCostoOrdenById(Long id);
    CostoOrden createCostoOrden(CostoOrden costoOrden);
    CostoOrden updateCostoOrden(Long id, CostoOrden costoOrden);
    void deleteCostoOrden(Long id);
}
