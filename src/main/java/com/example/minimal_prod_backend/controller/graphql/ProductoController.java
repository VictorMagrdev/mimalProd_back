package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.entity.Producto;
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
    @PreAuthorize("@customSecurity.hasPermission('PRODUCTO_TAG', 'READ')")
    public List<Producto> productos() {
        return productoService.getProductos();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('PRODUCTO_TAG', 'READ')")
    public Producto producto(@Argument Long id) {
        return productoService.getProductoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('PRODUCTO_TAG', 'CREATE')")
    public Producto createProducto(@Argument Producto producto) {
        return productoService.createProducto(producto);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('PRODUCTO_TAG', 'UPDATE')")
    public Producto updateProducto(@Argument Long id, @Argument Producto producto) {
        return productoService.updateProducto(id, producto);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('PRODUCTO_TAG', 'DELETE')")
    public boolean deleteProducto(@Argument Long id) {
        productoService.deleteProducto(id);
        return true;
    }
}
