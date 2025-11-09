package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.ProductoRequest;
import com.example.minimal_prod_backend.dto.Response.ProductoResponse;
import com.example.minimal_prod_backend.service.ProductoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('PRODUCTOS_TAG', 'READ')")
    public List<ProductoResponse> productos() {
        return productoService.getProductos();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('PRODUCTOS_TAG', 'READ')")
    public ProductoResponse producto(@Argument Long id) {
        return productoService.getProductoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('PRODUCTOS_TAG', 'CREATE')")
    public ProductoResponse createProducto(@Argument("input") ProductoRequest productoInput) {
        return productoService.createProducto(productoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('PRODUCTOS_TAG', 'UPDATE')")
    public ProductoResponse updateProducto(@Argument Long id, @Argument("input") ProductoRequest productoInput) {
        return productoService.updateProducto(id, productoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('PRODUCTOS_TAG', 'DELETE')")
    public boolean deleteProducto(@Argument Long id) {
        productoService.deleteProducto(id);
        return true;
    }
}
