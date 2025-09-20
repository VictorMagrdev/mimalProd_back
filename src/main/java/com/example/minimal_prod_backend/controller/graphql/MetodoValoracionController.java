package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.MetodoValoracionInput;
import com.example.minimal_prod_backend.dto.MetodoValoracionResponse;
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
        return metodoValoracionMapper.toResponse(metodoValoracionService.findById(id));
    }

    @MutationMapping
    public MetodoValoracionResponse createMetodoValoracion(@Argument MetodoValoracionInput input) {
        return metodoValoracionMapper.toResponse(metodoValoracionService.save(input));
    }

    @MutationMapping
    public MetodoValoracionResponse updateMetodoValoracion(@Argument Long id, @Argument MetodoValoracionInput input) {
        return metodoValoracionMapper.toResponse(metodoValoracionService.update(id, input));
    }

    @MutationMapping
    public boolean deleteMetodoValoracion(@Argument Long id) {
        metodoValoracionService.delete(id);
        return true;
    }
}
