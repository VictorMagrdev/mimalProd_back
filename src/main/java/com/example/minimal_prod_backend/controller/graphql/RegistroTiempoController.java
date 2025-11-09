package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.RegistroTiempoRequest;
import com.example.minimal_prod_backend.dto.Response.RegistroTiempoResponse;
import com.example.minimal_prod_backend.service.RegistroTiempoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegistroTiempoController {

    private final RegistroTiempoService registroTiempoService;

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('REGISTROS_TIEMPO_TAG', 'READ')")
    public List<RegistroTiempoResponse> registrosTiempo() {
        return registroTiempoService.getRegistrosTiempo();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('REGISTROS_TIEMPO_TAG', 'READ')")
    public RegistroTiempoResponse registroTiempo(@Argument Long id) {
        return registroTiempoService.getRegistroTiempoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('REGISTROS_TIEMPO_TAG', 'WRITE')")
    public RegistroTiempoResponse createRegistroTiempo(@Argument("input") RegistroTiempoRequest request) {
        return registroTiempoService.createRegistroTiempo(request);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('REGISTROS_TIEMPO_TAG', 'WRITE')")
    public RegistroTiempoResponse updateRegistroTiempo(@Argument Long id, @Argument("input") RegistroTiempoRequest request) {
        return registroTiempoService.updateRegistroTiempo(id, request);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('REGISTROS_TIEMPO_TAG', 'DELETE')")
    public boolean deleteRegistroTiempo(@Argument Long id) {
        registroTiempoService.deleteRegistroTiempo(id);
        return true;
    }
}
