package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.ProductoRequest;
import com.example.minimal_prod_backend.dto.ProductoResponse;

import java.util.List;

public interface ProductoService {
    List<ProductoResponse> getProductos();

    ProductoResponse getProductoById(Long id);

    ProductoResponse createProducto(ProductoRequest productoInput);

    ProductoResponse updateProducto(Long id, ProductoRequest productoInput);

    void deleteProducto(Long id);
}
