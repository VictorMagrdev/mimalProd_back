package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.MetodoValoracionRequest;
import com.example.minimal_prod_backend.dto.Response.MetodoValoracionResponse;
import com.example.minimal_prod_backend.entity.MetodoValoracion;
import com.example.minimal_prod_backend.mapper.MetodoValoracionMapper;
import com.example.minimal_prod_backend.service.MetodoValoracionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MetodoValoracionController {

    private final MetodoValoracionService metodoValoracionService;
    private final MetodoValoracionMapper metodoValoracionMapper;

    @QueryMapping
    public List<MetodoValoracionResponse> metodosValoracion() {
        return metodoValoracionService.findAll().stream()
                .map(metodoValoracionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @QueryMapping
    public MetodoValoracionResponse metodoValoracion(@Argument Long id) {
        MetodoValoracion metodoValoracion = metodoValoracionService.findById(id);
        if (metodoValoracion == null) return null;
        return metodoValoracionMapper.toResponse(metodoValoracion);
    }

    @MutationMapping
    public MetodoValoracionResponse createMetodoValoracion(@Argument MetodoValoracionRequest input) {
        MetodoValoracion metodoValoracion = metodoValoracionService.save(input);
        return metodoValoracionMapper.toResponse(metodoValoracion);
    }

    @MutationMapping
    public MetodoValoracionResponse updateMetodoValoracion(@Argument Long id, @Argument MetodoValoracionRequest input) {
        MetodoValoracion metodoValoracion = metodoValoracionService.update(id, input);
        if (metodoValoracion == null) return null;
        return metodoValoracionMapper.toResponse(metodoValoracion);
    }

    @MutationMapping
    public boolean deleteMetodoValoracion(@Argument Long id) {
        metodoValoracionService.delete(id);
        return true;
    }
}
