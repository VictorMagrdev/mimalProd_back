package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.ConteoCiclicoInput;
import com.example.minimal_prod_backend.dto.ConteoCiclicoResponse;
import com.example.minimal_prod_backend.service.ConteoCiclicoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ConteoCiclicoController {

    private final ConteoCiclicoService conteoCiclicoService;

    public ConteoCiclicoController(ConteoCiclicoService conteoCiclicoService) {
        this.conteoCiclicoService = conteoCiclicoService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('CONTEO_CICLICO_TAG', 'READ')")
    public List<ConteoCiclicoResponse> conteosCiclicos() {
        return conteoCiclicoService.getConteosCiclicos();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('CONTEO_CICLICO_TAG', 'READ')")
    public ConteoCiclicoResponse conteoCiclico(@Argument Long id) {
        return conteoCiclicoService.getConteoCiclicoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('CONTEO_CICLICO_TAG', 'CREATE')")
    public ConteoCiclicoResponse createConteoCiclico(@Argument("input") ConteoCiclicoInput conteoCiclicoInput) {
        return conteoCiclicoService.createConteoCiclico(conteoCiclicoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('CONTEO_CICLICO_TAG', 'UPDATE')")
    public ConteoCiclicoResponse updateConteoCiclico(@Argument Long id, @Argument("input") ConteoCiclicoInput conteoCiclicoInput) {
        return conteoCiclicoService.updateConteoCiclico(id, conteoCiclicoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('CONTEO_CICLICO_TAG', 'DELETE')")
    public boolean deleteConteoCiclico(@Argument Long id) {
        conteoCiclicoService.deleteConteoCiclico(id);
        return true;
    }
}
