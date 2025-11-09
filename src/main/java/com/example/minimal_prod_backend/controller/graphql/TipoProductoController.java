package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.TipoProductoRequest;
import com.example.minimal_prod_backend.dto.Response.TipoProductoResponse;
import com.example.minimal_prod_backend.service.TipoProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TipoProductoController {

    private final TipoProductoService tipoProductoService;

    @QueryMapping
    public List<TipoProductoResponse> tiposProducto() {
        return tipoProductoService.getTiposProducto();
    }

    @QueryMapping
    public TipoProductoResponse tipoProducto(@Argument Long id) {
        return tipoProductoService.getTipoProductoById(id);
    }

    @MutationMapping
    public TipoProductoResponse createTipoProducto(@Argument TipoProductoRequest input) {
        return tipoProductoService.createTipoProducto(input);
    }

    @MutationMapping
    public TipoProductoResponse updateTipoProducto(@Argument Long id, @Argument TipoProductoRequest input) {
        return tipoProductoService.updateTipoProducto(id, input);
    }

    @MutationMapping
    public boolean deleteTipoProducto(@Argument Long id) {
        tipoProductoService.deleteTipoProducto(id);
        return true;
    }
}
