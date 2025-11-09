package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.TipoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.Response.TipoIncidenciaResponse;
import com.example.minimal_prod_backend.service.TipoIncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TipoIncidenciaController {

    private final TipoIncidenciaService service;

    @QueryMapping
    public List<TipoIncidenciaResponse> tiposIncidencia() {
        return service.findAll();
    }

    @QueryMapping
    public TipoIncidenciaResponse tipoIncidencia(@Argument Long id) {
        return service.findById(id);
    }

    @MutationMapping
    public TipoIncidenciaResponse crearTipoIncidencia(@Argument TipoIncidenciaRequest input) {
        return service.create(input);
    }

    @MutationMapping
    public Boolean eliminarTipoIncidencia(@Argument Long id) {
        service.delete(id);
        return true;
    }
}
