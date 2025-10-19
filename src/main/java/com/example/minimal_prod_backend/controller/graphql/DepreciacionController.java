package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.DepreciacionRequest;
import com.example.minimal_prod_backend.dto.DepreciacionResponse;
import com.example.minimal_prod_backend.service.DepreciacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DepreciacionController {

    private final DepreciacionService depreciacionService;

    @QueryMapping
    public List<DepreciacionResponse> depreciaciones() {
        return depreciacionService.getDepreciaciones();
    }

    @QueryMapping
    public DepreciacionResponse depreciacion(@Argument Long id) {
        return depreciacionService.getDepreciacionById(id);
    }

    @MutationMapping
    public DepreciacionResponse createDepreciacion(@Argument("input") DepreciacionRequest request) {
        return depreciacionService.createDepreciacion(request);
    }

    @MutationMapping
    public DepreciacionResponse updateDepreciacion(@Argument Long id, @Argument("input") DepreciacionRequest request) {
        return depreciacionService.updateDepreciacion(id, request);
    }

    @MutationMapping
    public boolean deleteDepreciacion(@Argument Long id) {
        depreciacionService.deleteDepreciacion(id);
        return true;
    }
}
