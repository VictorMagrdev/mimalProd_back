package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.ProductoRequest;
import com.example.minimal_prod_backend.dto.Response.ProductoResponse;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.ProductoMapper;
import com.example.minimal_prod_backend.repository.MetodoValoracionRepository;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.repository.TipoProductoRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final MetodoValoracionRepository metodoValoracionRepository;
    private final TipoProductoRepository tipoProductoRepository;
    private final ProductoMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> getProductos() {
        return mapper.toResponseList(productoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponse getProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + id));
        return mapper.toResponse(producto);
    }

    @Override
    @Transactional
    public ProductoResponse createProducto(ProductoRequest input) {
        Producto entity = mapper.toEntity(input);

        entity.setMetodoValoracion(metodoValoracionRepository.findById(input.metodoValoracionId())
                .orElseThrow(() -> new RuntimeException("MetodoValoracion no encontrado")));
        entity.setTipo(tipoProductoRepository.findById(input.tipoId())
                .orElseThrow(() -> new RuntimeException("TipoProducto no encontrado")));
        entity.setUnidadBase(unidadMedidaRepository.findById(input.unidadBaseId())
                .orElseThrow(() -> new RuntimeException("UnidadMedida no encontrado")));

        return mapper.toResponse(productoRepository.save(entity));
    }


    @Override
    @Transactional
    public ProductoResponse updateProducto(Long id, ProductoRequest input) {
        Producto entity = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + id));

        mapper.updateEntityFromInput(input, entity);

        if (input.unidadBaseId() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(input.unidadBaseId())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + input.unidadBaseId()));
            entity.setUnidadBase(unidad);
        } else {
            entity.setUnidadBase(null);
        }

        return mapper.toResponse(productoRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
