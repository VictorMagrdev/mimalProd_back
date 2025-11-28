package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.ProductoRequest;
import com.example.minimal_prod_backend.dto.Response.MetodoValoracionResponse;
import com.example.minimal_prod_backend.dto.Response.ProductoResponse;
import com.example.minimal_prod_backend.dto.Response.TipoProductoResponse;
import com.example.minimal_prod_backend.dto.Response.UnidadMedidaResponse;
import com.example.minimal_prod_backend.service.MetodoValoracionService;
import com.example.minimal_prod_backend.service.ProductoService;
import com.example.minimal_prod_backend.service.TipoProductoService;
import com.example.minimal_prod_backend.service.UnidadMedidaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductoController {

    private final ProductoService productoService;
    private final TipoProductoService tipoProductoService;
    private final MetodoValoracionService metodoValoracionService;
    private final UnidadMedidaService unidadMedidaService;

    public ProductoController(ProductoService productoService, TipoProductoService tipoProductoService, MetodoValoracionService metodoValoracionService, UnidadMedidaService unidadMedidaService) {
        this.productoService = productoService;
        this.tipoProductoService = tipoProductoService;
        this.metodoValoracionService = metodoValoracionService;
        this.unidadMedidaService = unidadMedidaService;
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

    @SchemaMapping(typeName = "Producto", field = "tipo")
    public TipoProductoResponse tipo(ProductoResponse producto) {
        return tipoProductoService.getTipoProductoById(producto.tipoId());
    }

    @SchemaMapping(typeName = "Producto", field = "metodoValoracion")
    public MetodoValoracionResponse metodoValoracion(ProductoResponse producto) {
        return metodoValoracionService.GetById(producto.metodoValoracionId());
    }
    @SchemaMapping(typeName = "Producto", field = "unidadBase")
    public UnidadMedidaResponse unidadBase(ProductoResponse producto) {
        return unidadMedidaService.getUnidadMedidaById(producto.unidadBaseId());
    }
}
