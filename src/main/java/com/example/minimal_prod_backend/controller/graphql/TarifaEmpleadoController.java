package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.TarifaEmpleadoRequest;
import com.example.minimal_prod_backend.dto.Response.TarifaEmpleadoResponse;
import com.example.minimal_prod_backend.service.TarifaEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TarifaEmpleadoController {

    private final TarifaEmpleadoService tarifaEmpleadoService;

    @QueryMapping
    public List<TarifaEmpleadoResponse> tarifasEmpleado() {
        return tarifaEmpleadoService.getTarifasEmpleado();
    }

    @QueryMapping
    public TarifaEmpleadoResponse tarifaEmpleado(@Argument Long id) {
        return tarifaEmpleadoService.getTarifaEmpleadoById(id);
    }

    @MutationMapping
    public TarifaEmpleadoResponse createTarifaEmpleado(@Argument("input") TarifaEmpleadoRequest request) {
        return tarifaEmpleadoService.createTarifaEmpleado(request);
    }

    @MutationMapping
    public TarifaEmpleadoResponse updateTarifaEmpleado(@Argument Long id, @Argument("input") TarifaEmpleadoRequest request) {
        return tarifaEmpleadoService.updateTarifaEmpleado(id, request);
    }

    @MutationMapping
    public boolean deleteTarifaEmpleado(@Argument Long id) {
        tarifaEmpleadoService.deleteTarifaEmpleado(id);
        return true;
    }
}
