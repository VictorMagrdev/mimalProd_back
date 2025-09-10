package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.ProductoInput;
import com.example.minimal_prod_backend.dto.ProductoResponse;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> getProductos() {
        return productoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponse getProductoById(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + id));
        return toResponse(producto);
    }

    @Override
    @Transactional
    public ProductoResponse createProducto(ProductoInput productoInput) {
        Producto producto = toEntity(productoInput);
        return toResponse(productoRepository.save(producto));
    }

    @Override
    @Transactional
    public ProductoResponse updateProducto(Integer id, ProductoInput productoInput) {
        Producto existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + id));
        updateEntityFromInput(productoInput, existingProducto);
        return toResponse(productoRepository.save(existingProducto));
    }

    @Override
    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    private ProductoResponse toResponse(Producto entity) {
        if (entity == null) return null;
        ProductoResponse dto = new ProductoResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setCostoBase(entity.getCostoBase());
        dto.setCreadoEn(entity.getCreadoEn());

        if (entity.getUnidadBase() != null) {
            UnidadMedidaResponse unidadDto = new UnidadMedidaResponse();
            unidadDto.setId(entity.getUnidadBase().getId());
            unidadDto.setCodigo(entity.getUnidadBase().getCodigo());
            unidadDto.setNombre(entity.getUnidadBase().getNombre());
            unidadDto.setAbreviatura(entity.getUnidadBase().getAbreviatura());
            // Avoid deep nesting for tipo
            dto.setUnidadBase(unidadDto);
        }

        return dto;
    }

    private Producto toEntity(ProductoInput dto) {
        if (dto == null) return null;
        Producto entity = new Producto();
        updateEntityFromInput(dto, entity);
        return entity;
    }

    private void updateEntityFromInput(ProductoInput dto, Producto entity) {
        if (dto == null || entity == null) return;

        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setCostoBase(dto.getCostoBase());

        if (dto.getIdUnidadBase() != null) {
            UnidadMedida unidadMedida = unidadMedidaRepository.findById(dto.getIdUnidadBase())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getIdUnidadBase()));
            entity.setUnidadBase(unidadMedida);
        } else {
            entity.setUnidadBase(null);
        }
    }
}
