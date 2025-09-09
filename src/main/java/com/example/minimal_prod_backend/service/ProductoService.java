package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> getProductos();
    Producto getProductoById(Long id);
    Producto createProducto(Producto producto);
    Producto updateProducto(Long id, Producto producto);
    void deleteProducto(Long id);
}
