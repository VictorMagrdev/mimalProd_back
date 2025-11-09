package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.ParametroPlanificacionRequest;
import com.example.minimal_prod_backend.dto.Response.ParametroPlanificacionResponse;
import com.example.minimal_prod_backend.service.ParametroPlanificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ParametroPlanificacionController {
    private final ParametroPlanificacionService parametroPlanificacionService;

    @QueryMapping
    public List<ParametroPlanificacionResponse> parametrosClasificacion() {
        return parametroPlanificacionService.getAllParametros();
    }

    @QueryMapping
    public ParametroPlanificacionResponse parametroPlanificacion(@Argument Long id) {
        return parametroPlanificacionService.getParametroById(id);
    }
    @QueryMapping
    public ParametroPlanificacionResponse parametroByProductoId(@Argument Long productoId) {
        return parametroPlanificacionService.getParametroByProductoId(productoId);
    }
    @MutationMapping
    public ParametroPlanificacionResponse createParametroPlanificacion(@Argument ParametroPlanificacionRequest input) {
        return parametroPlanificacionService.createParametro(input);
    }

    @MutationMapping
    public ParametroPlanificacionResponse updateParametroPlanificacion(@Argument Long id, @Argument ParametroPlanificacionRequest input) {
        return parametroPlanificacionService.updateParametro(id, input);
    }

    @MutationMapping
    public boolean deleteParametroPlanificacion(@Argument Long id) {
        parametroPlanificacionService.deleteParametro(id);
        return true;
    }
}
