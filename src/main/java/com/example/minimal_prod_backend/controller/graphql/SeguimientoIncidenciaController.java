package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.SeguimientoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.SeguimientoIncidenciaResponse;
import com.example.minimal_prod_backend.service.SeguimientoIncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SeguimientoIncidenciaController {

    private final SeguimientoIncidenciaService service;

    @QueryMapping
    public List<SeguimientoIncidenciaResponse> seguimientoIncidencias(@Argument Long incidenciaId) {
        return service.findByIncidencia(incidenciaId);
    }

    @MutationMapping
    public SeguimientoIncidenciaResponse createSeguimientoIncidencia(@Argument SeguimientoIncidenciaRequest input) {
        return service.create(input);
    }

    @MutationMapping
    public Boolean deleteSeguimientoIncidencia(@Argument Long id) {
        service.delete(id);
        return true;
    }
}
