package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.PuntoReordenInput;
import com.example.minimal_prod_backend.dto.PuntoReordenResponse;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.entity.PuntoReorden;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.PuntoReordenMapper;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.repository.PuntoReordenRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.service.PuntoReordenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PuntoReordenServiceImpl implements PuntoReordenService {

    private final PuntoReordenRepository puntoReordenRepository;
    private final ProductoRepository productoRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final PuntoReordenMapper mapper;

    @Override
    public List<PuntoReordenResponse> getPuntosReorden() {
        return mapper.toResponseList(puntoReordenRepository.findAll());
    }

    @Override
    public PuntoReordenResponse getPuntoReordenById(Long id) {
        PuntoReorden puntoReorden = puntoReordenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PuntoReorden not found with id: " + id));
        return mapper.toResponse(puntoReorden);
    }

    @Override
    public PuntoReordenResponse createPuntoReorden(PuntoReordenInput input) {
        PuntoReorden entity = mapper.toEntity(input);

        if (input.getProductoId() != null) {
            Producto producto = productoRepository.findById(input.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + input.getProductoId()));
            entity.setProducto(producto);
        }

        if (input.getUnidadId() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(input.getUnidadId())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + input.getUnidadId()));
            entity.setUnidad(unidad);
        }

        return mapper.toResponse(puntoReordenRepository.save(entity));
    }

    @Override
    public PuntoReordenResponse updatePuntoReorden(Long id, PuntoReordenInput input) {
        PuntoReorden entity = puntoReordenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PuntoReorden not found with id: " + id));

        mapper.updateEntityFromInput(input, entity);

        if (input.getProductoId() != null) {
            Producto producto = productoRepository.findById(input.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + input.getProductoId()));
            entity.setProducto(producto);
        }

        if (input.getUnidadId() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(input.getUnidadId())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + input.getUnidadId()));
            entity.setUnidad(unidad);
        }

        return mapper.toResponse(puntoReordenRepository.save(entity));
    }

    @Override
    public void deletePuntoReorden(Long id) {
        puntoReordenRepository.deleteById(id);
    }
}
