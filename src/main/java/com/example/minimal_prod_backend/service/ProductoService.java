package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.ProductoInput;
import com.example.minimal_prod_backend.dto.ProductoResponse;

import java.util.List;

public interface ProductoService {
    List<ProductoResponse> getProductos();
    ProductoResponse getProductoById(Integer id);
    ProductoResponse createProducto(ProductoInput productoInput);
    ProductoResponse updateProducto(Integer id, ProductoInput productoInput);
    void deleteProducto(Integer id);
}
