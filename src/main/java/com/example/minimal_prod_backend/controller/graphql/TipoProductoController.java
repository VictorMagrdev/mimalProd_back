package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.TipoProductoInput;
import com.example.minimal_prod_backend.dto.TipoProductoResponse;
import com.example.minimal_prod_backend.mapper.TipoProductoMapper;
import com.example.minimal_prod_backend.service.TipoProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TipoProductoController {

    private final TipoProductoService tipoProductoService;
    private final TipoProductoMapper tipoProductoMapper;

    @QueryMapping
    public List<TipoProductoResponse> tiposProducto() {
        return tipoProductoService.findAll().stream()
                .map(tipoProductoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @QueryMapping
    public TipoProductoResponse tipoProducto(@Argument Long id) {
        return tipoProductoMapper.toResponse(tipoProductoService.findById(id));
    }

    @MutationMapping
    public TipoProductoResponse createTipoProducto(@Argument TipoProductoInput input) {
        return tipoProductoMapper.toResponse(tipoProductoService.save(input));
    }

    @MutationMapping
    public TipoProductoResponse updateTipoProducto(@Argument Long id, @Argument TipoProductoInput input) {
        return tipoProductoMapper.toResponse(tipoProductoService.update(id, input));
    }

    @MutationMapping
    public boolean deleteTipoProducto(@Argument Long id) {
        tipoProductoService.delete(id);
        return true;
    }
}
