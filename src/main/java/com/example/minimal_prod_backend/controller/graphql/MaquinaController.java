package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.MaquinaRequest;
import com.example.minimal_prod_backend.dto.Response.MaquinaResponse;
import com.example.minimal_prod_backend.service.MaquinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MaquinaController {

    private final MaquinaService maquinaService;

    @QueryMapping
    public List<MaquinaResponse> maquinas() {
        return maquinaService.getMaquinas();
    }

    @QueryMapping
    public MaquinaResponse maquina(@Argument Long id) {
        return maquinaService.getMaquinaById(id);
    }

    @MutationMapping
    public MaquinaResponse createMaquina(@Argument("input") MaquinaRequest request) {
        return maquinaService.createMaquina(request);
    }

    @MutationMapping
    public MaquinaResponse updateMaquina(@Argument Long id, @Argument("input") MaquinaRequest request) {
        return maquinaService.updateMaquina(id, request);
    }

    @MutationMapping
    public boolean deleteMaquina(@Argument Long id) {
        maquinaService.deleteMaquina(id);
        return true;
    }
}
