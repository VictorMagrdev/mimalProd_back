package com.example.minimal_prod_backend.service;


import com.example.minimal_prod_backend.dto.Request.RequerimientoMaterialRequest;
import com.example.minimal_prod_backend.dto.Response.RequerimientoMaterialResponse;

import java.util.List;

public interface RequerimientoMaterialService {
    List<RequerimientoMaterialResponse> getAllRequerimientos();

    RequerimientoMaterialResponse getRequerimientoById(Long id);

    RequerimientoMaterialResponse createRequerimiento(RequerimientoMaterialRequest request);

    RequerimientoMaterialResponse updateRequerimiento(Long id, RequerimientoMaterialRequest request);

    List<RequerimientoMaterialResponse> getRequerimientosByProductoId(Long productoId);

    List<RequerimientoMaterialResponse> getRequerimientosByOrdenProduccionId(Long ordenProduccionId);

    void deleteRequerimiento(Long id);
}
