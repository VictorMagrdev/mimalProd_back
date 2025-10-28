package com.example.minimal_prod_backend.service.impl;



import com.example.minimal_prod_backend.dto.RequerimientoMaterialRequest;
import com.example.minimal_prod_backend.dto.RequerimientoMaterialResponse;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.entity.RequerimientoMaterial;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.RequerimientoMaterialMapper;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.repository.RequerimientoMaterialRepository;
import com.example.minimal_prod_backend.service.RequerimientoMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequerimientoMaterialServiceImpl implements RequerimientoMaterialService {

    private final RequerimientoMaterialRepository requerimientoMaterialRepository;
    private final ProductoRepository productoRepository;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final RequerimientoMaterialMapper requerimientoMaterialMapper;

    @Override
    public List<RequerimientoMaterialResponse> getAllRequerimientos() {
        return requerimientoMaterialRepository.findAll().stream()
                .map(requerimientoMaterialMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RequerimientoMaterialResponse getRequerimientoById(Long id) {
        RequerimientoMaterial requerimiento = requerimientoMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RequerimientoMaterial not found with id: " + id));
        return requerimientoMaterialMapper.toResponse(requerimiento);
    }

    @Override
    public RequerimientoMaterialResponse createRequerimiento(RequerimientoMaterialRequest request) {
        Producto producto = productoRepository.findById(request.productoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + request.productoId()));

        OrdenProduccion ordenProduccion = request.ordenProduccionId() != null ?
                ordenProduccionRepository.findById(request.ordenProduccionId())
                        .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + request.ordenProduccionId())) : null;

        RequerimientoMaterial requerimiento = requerimientoMaterialMapper.toEntity(request);
        requerimiento.setProducto(producto);
        requerimiento.setOrdenProduccion(ordenProduccion);

        return requerimientoMaterialMapper.toResponse(requerimientoMaterialRepository.save(requerimiento));
    }

    @Override
    public RequerimientoMaterialResponse updateRequerimiento(Long id, RequerimientoMaterialRequest request) {
        RequerimientoMaterial existingRequerimiento = requerimientoMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RequerimientoMaterial not found with id: " + id));

        Producto producto = productoRepository.findById(request.productoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + request.productoId()));

        OrdenProduccion ordenProduccion = request.ordenProduccionId() != null ?
                ordenProduccionRepository.findById(request.ordenProduccionId())
                        .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found with id: " + request.ordenProduccionId())) : null;

        requerimientoMaterialMapper.updateEntityFromRequest(request, existingRequerimiento);
        existingRequerimiento.setProducto(producto);
        existingRequerimiento.setOrdenProduccion(ordenProduccion);

        return requerimientoMaterialMapper.toResponse(requerimientoMaterialRepository.save(existingRequerimiento));
    }

    @Override
    public void deleteRequerimiento(Long id) {
        if (!requerimientoMaterialRepository.existsById(id)) {
            throw new ResourceNotFoundException("RequerimientoMaterial not found with id: " + id);
        }
        requerimientoMaterialRepository.deleteById(id);
    }
}