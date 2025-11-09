package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.TipoOrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.Response.TipoOrdenTrabajoResponse;
import com.example.minimal_prod_backend.service.TipoOrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TipoOrdenTrabajoController {

    @Autowired
    private TipoOrdenTrabajoService tipoOrdenTrabajoService;


    @QueryMapping
    public List<TipoOrdenTrabajoResponse> tiposOrdenTrabajo() {
        return tipoOrdenTrabajoService.getTiposOrdenTrabajo();
    }

    @QueryMapping
    public TipoOrdenTrabajoResponse tipoOrdenTrabajoById(@Argument Long id) {
        return tipoOrdenTrabajoService.getTipoOrdenTrabajoById(id);
    }

    @MutationMapping
    public TipoOrdenTrabajoResponse createTipoOrdenTrabajo(@Argument TipoOrdenTrabajoRequest input) {
        return tipoOrdenTrabajoService.createTipoOrdenTrabajo(input);
    }

    @MutationMapping
    public TipoOrdenTrabajoResponse actualizarTipoOrdenTrabajo(
            @Argument Long id,
            @Argument TipoOrdenTrabajoRequest input
    ) {
        return tipoOrdenTrabajoService.updateTipoOrdenTrabajo(id, input);
    }

    @MutationMapping
    public Boolean eliminarTipoOrdenTrabajo(@Argument Long id) {
        tipoOrdenTrabajoService.deleteTipoOrdenTrabajo(id);
        return true;
    }
}
