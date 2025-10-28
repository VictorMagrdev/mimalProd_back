package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.RequerimientoMaterialRequest;
import com.example.minimal_prod_backend.dto.RequerimientoMaterialResponse;
import com.example.minimal_prod_backend.service.RequerimientoMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RequerimientoMaterialController {
     private final RequerimientoMaterialService requerimientoMaterialService;

    @QueryMapping
    public List<RequerimientoMaterialResponse> requerimientosMateriales() {
        return requerimientoMaterialService.getAllRequerimientos();
    }

    @QueryMapping
    public RequerimientoMaterialResponse requerimientoMaterial(@Argument Long id) {
        return requerimientoMaterialService.getRequerimientoById(id);
    }

    @MutationMapping
    public RequerimientoMaterialResponse createRequerimientoMaterial(@Argument RequerimientoMaterialRequest input) {
        return requerimientoMaterialService.createRequerimiento(input);
    }

    @MutationMapping
    public RequerimientoMaterialResponse updateRequerimientoMaterial(@Argument Long id, @Argument RequerimientoMaterialRequest input) {
        return requerimientoMaterialService.updateRequerimiento(id, input);
    }

    @MutationMapping
    public boolean deleteRequerimientoMaterial(@Argument Long id) {
        requerimientoMaterialService.deleteRequerimiento(id);
        return true;
    }
}
