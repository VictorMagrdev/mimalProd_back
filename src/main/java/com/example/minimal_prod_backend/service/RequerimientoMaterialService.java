package com.example.minimal_prod_backend.service;


import com.example.minimal_prod_backend.dto.RequerimientoMaterialRequest;
import com.example.minimal_prod_backend.dto.RequerimientoMaterialResponse;

import java.util.List;

public interface RequerimientoMaterialService {
    List<RequerimientoMaterialResponse> getAllRequerimientos();
    RequerimientoMaterialResponse getRequerimientoById(Long id);
    RequerimientoMaterialResponse createRequerimiento(RequerimientoMaterialRequest request);
    RequerimientoMaterialResponse updateRequerimiento(Long id, RequerimientoMaterialRequest request);
    void deleteRequerimiento(Long id);
}
