package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.ProductoInput;
import com.example.minimal_prod_backend.dto.ProductoResponse;

import java.util.List;

public interface ProductoService {
    List<ProductoResponse> getProductos();

    ProductoResponse getProductoById(Long id);

    ProductoResponse createProducto(ProductoInput productoInput);

    ProductoResponse updateProducto(Long id, ProductoInput productoInput);

    void deleteProducto(Long id);
}
