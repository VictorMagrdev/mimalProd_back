package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.EstructuraProductoRequest;
import com.example.minimal_prod_backend.dto.EstructuraProductoResponse;
import com.example.minimal_prod_backend.service.EstructuraProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EstructuraProductoController {
    private final EstructuraProductoService estructuraProductoService;

    @QueryMapping
    public List<EstructuraProductoResponse> estructurasProductos() {
        return estructuraProductoService.getAllEstructuras();
    }

    @QueryMapping
    public EstructuraProductoResponse estructuraProducto(@Argument Long id) {
        return estructuraProductoService.getEstructuraById(id);
    }
    @QueryMapping
    public List<EstructuraProductoResponse> estructurasByProductoPadreId(@Argument Long productoPadreId) {
        return estructuraProductoService.getEstructurasByProductoPadreId(productoPadreId);
    }

    @MutationMapping
    public EstructuraProductoResponse createEstructuraProducto(@Argument EstructuraProductoRequest input) {
        return estructuraProductoService.createEstructura(input);
    }

    @MutationMapping
    public EstructuraProductoResponse updateEstructuraProducto(@Argument Long id, @Argument EstructuraProductoRequest input) {
        return estructuraProductoService.updateEstructura(id, input);
    }

    @MutationMapping
    public boolean deleteEstructuraProducto(@Argument Long id) {
        estructuraProductoService.deleteEstructura(id);
        return true;
    }
}
