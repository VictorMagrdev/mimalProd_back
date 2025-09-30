package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.AsignacionRequest;
import com.example.minimal_prod_backend.dto.AsignacionResponse;
import com.example.minimal_prod_backend.service.AsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AsignacionController {

    private final AsignacionService asignacionService;

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ASIGNACIONES_TAG', 'READ')")
    public List<AsignacionResponse> asignaciones() {
        return asignacionService.getAsignaciones();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('ASIGNACIONES_TAG', 'READ')")
    public AsignacionResponse asignacion(@Argument Long id) {
        return asignacionService.getAsignacionById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ASIGNACIONES_TAG', 'WRITE')")
    public AsignacionResponse createAsignacion(@Argument("input") AsignacionRequest request) {
        return asignacionService.createAsignacion(request);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ASIGNACIONES_TAG', 'WRITE')")
    public AsignacionResponse updateAsignacion(@Argument Long id, @Argument("input") AsignacionRequest request) {
        return asignacionService.updateAsignacion(id, request);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('ASIGNACIONES_TAG', 'DELETE')")
    public boolean deleteAsignacion(@Argument Long id) {
        asignacionService.deleteAsignacion(id);
        return true;
    }
}
