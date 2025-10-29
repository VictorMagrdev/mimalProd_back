package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.ParametroPlanificacionRequest;
import com.example.minimal_prod_backend.dto.ParametroPlanificacionResponse;
import com.example.minimal_prod_backend.entity.ParametroPlanificacion;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.ParametroPlanificacionMapper;
import com.example.minimal_prod_backend.repository.ParametroPlanificacionRepository;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.service.ParametroPlanificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParametroPlanificacionServiceImpl implements ParametroPlanificacionService {

    private final ParametroPlanificacionRepository parametroPlanificacionRepository;
    private final ProductoRepository productoRepository;
    private final ParametroPlanificacionMapper parametroPlanificacionMapper;

    @Override
    public List<ParametroPlanificacionResponse> getAllParametros() {
        return parametroPlanificacionRepository.findAll().stream()
                .map(parametroPlanificacionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ParametroPlanificacionResponse getParametroById(Long id) {
        ParametroPlanificacion parametro = parametroPlanificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ParametroPlanificacion not found with id: " + id));
        return parametroPlanificacionMapper.toResponse(parametro);
    }

    @Override
    public ParametroPlanificacionResponse getParametroByProductoId(Long productoId) {
        ParametroPlanificacion parametro = parametroPlanificacionRepository.findByProductoId(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("ParametroPlanificacion not found for producto id: " + productoId));
        return parametroPlanificacionMapper.toResponse(parametro);
    }

    @Override
    public ParametroPlanificacionResponse createParametro(ParametroPlanificacionRequest request) {
        Producto producto = productoRepository.findById(request.productoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + request.productoId()));

        ParametroPlanificacion parametro = parametroPlanificacionMapper.toEntity(request);
        parametro.setProducto(producto);

        return parametroPlanificacionMapper.toResponse(parametroPlanificacionRepository.save(parametro));
    }

    @Override
    public ParametroPlanificacionResponse updateParametro(Long id, ParametroPlanificacionRequest request) {
        ParametroPlanificacion existingParametro = parametroPlanificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ParametroPlanificacion not found with id: " + id));

        Producto producto = productoRepository.findById(request.productoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + request.productoId()));

        parametroPlanificacionMapper.updateEntityFromRequest(request, existingParametro);
        existingParametro.setProducto(producto);

        return parametroPlanificacionMapper.toResponse(parametroPlanificacionRepository.save(existingParametro));
    }

    @Override
    public void deleteParametro(Long id) {
        if (!parametroPlanificacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("ParametroPlanificacion not found with id: " + id);
        }
        parametroPlanificacionRepository.deleteById(id);
    }
}
