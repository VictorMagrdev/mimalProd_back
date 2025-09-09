package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) {
        return productoRepository.findById(id).map(existingProducto -> {
            existingProducto.setCodigo(producto.getCodigo());
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setUnidadBase(producto.getUnidadBase());
            existingProducto.setCostoBase(producto.getCostoBase());
            return productoRepository.save(existingProducto);
        }).orElse(null);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
