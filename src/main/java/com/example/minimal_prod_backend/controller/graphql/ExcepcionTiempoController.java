package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.ExcepcionTiempoRequest;
import com.example.minimal_prod_backend.dto.ExcepcionTiempoResponse;
import com.example.minimal_prod_backend.service.ExcepcionTiempoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExcepcionTiempoController {

    private final ExcepcionTiempoService excepcionTiempoService;

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('EXCEPCIONES_TIEMPO_TAG', 'READ')")
    public List<ExcepcionTiempoResponse> excepcionesTiempo() {
        return excepcionTiempoService.getExcepcionesTiempo();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('EXCEPCIONES_TIEMPO_TAG', 'READ')")
    public ExcepcionTiempoResponse excepcionTiempo(@Argument Long id) {
        return excepcionTiempoService.getExcepcionTiempoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('EXCEPCIONES_TIEMPO_TAG', 'WRITE')")
    public ExcepcionTiempoResponse createExcepcionTiempo(@Argument("input") ExcepcionTiempoRequest request) {
        return excepcionTiempoService.createExcepcionTiempo(request);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('EXCEPCIONES_TIEMPO_TAG', 'WRITE')")
    public ExcepcionTiempoResponse updateExcepcionTiempo(@Argument Long id, @Argument("input") ExcepcionTiempoRequest request) {
        return excepcionTiempoService.updateExcepcionTiempo(id, request);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('EXCEPCIONES_TIEMPO_TAG', 'DELETE')")
    public boolean deleteExcepcionTiempo(@Argument Long id) {
        excepcionTiempoService.deleteExcepcionTiempo(id);
        return true;
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('EXCEPCIONES_TIEMPO_TAG', 'WRITE')")
    public ExcepcionTiempoResponse resolveExcepcionTiempo(@Argument Long id) {
        return excepcionTiempoService.resolveExcepcionTiempo(id);
    }
}
