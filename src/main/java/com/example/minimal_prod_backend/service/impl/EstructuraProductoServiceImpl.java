package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.EstructuraProductoRequest;
import com.example.minimal_prod_backend.dto.EstructuraProductoResponse;
import com.example.minimal_prod_backend.entity.EstructuraProducto;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.EstructuraProductoMapper;
import com.example.minimal_prod_backend.repository.EstructuraProductoRepository;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.service.EstructuraProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstructuraProductoServiceImpl implements EstructuraProductoService {

    private final EstructuraProductoRepository estructuraProductoRepository;
    private final ProductoRepository productoRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final EstructuraProductoMapper estructuraProductoMapper;

    @Override
    public List<EstructuraProductoResponse> getAllEstructuras() {
        return estructuraProductoRepository.findAll().stream()
                .map(estructuraProductoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EstructuraProductoResponse getEstructuraById(Long id) {
        EstructuraProducto estructura = estructuraProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstructuraProducto not found with id: " + id));
        return estructuraProductoMapper.toResponse(estructura);
    }


    @Override
    public EstructuraProductoResponse createEstructura(EstructuraProductoRequest request) {
        Producto productoPadre = productoRepository.findById(request.productoPadreId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto padre not found with id: " + request.productoPadreId()));

        Producto productoHijo = productoRepository.findById(request.productoHijoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto hijo not found with id: " + request.productoHijoId()));

        UnidadMedida unidad = request.unidadId() != null ?
                unidadMedidaRepository.findById(request.unidadId())
                        .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + request.unidadId())) : null;

        EstructuraProducto estructura = estructuraProductoMapper.toEntity(request);
        estructura.setProductoPadre(productoPadre);
        estructura.setProductoHijo(productoHijo);
        estructura.setUnidad(unidad);

        return estructuraProductoMapper.toResponse(estructuraProductoRepository.save(estructura));
    }

    @Override
    public EstructuraProductoResponse updateEstructura(Long id, EstructuraProductoRequest request) {
        EstructuraProducto existingEstructura = estructuraProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstructuraProducto not found with id: " + id));

        Producto productoPadre = productoRepository.findById(request.productoPadreId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto padre not found with id: " + request.productoPadreId()));

        Producto productoHijo = productoRepository.findById(request.productoHijoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto hijo not found with id: " + request.productoHijoId()));

        UnidadMedida unidad = request.unidadId() != null ?
                unidadMedidaRepository.findById(request.unidadId())
                        .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + request.unidadId())) : null;

        estructuraProductoMapper.updateEntityFromRequest(request, existingEstructura);
        existingEstructura.setProductoPadre(productoPadre);
        existingEstructura.setProductoHijo(productoHijo);
        existingEstructura.setUnidad(unidad);

        return estructuraProductoMapper.toResponse(estructuraProductoRepository.save(existingEstructura));
    }

    @Override
    public void deleteEstructura(Long id) {
        if (!estructuraProductoRepository.existsById(id)) {
            throw new ResourceNotFoundException("EstructuraProducto not found with id: " + id);
        }
        estructuraProductoRepository.deleteById(id);
    }
}
