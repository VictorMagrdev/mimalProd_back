package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.TarifaEmpleadoRequest;
import com.example.minimal_prod_backend.dto.TarifaEmpleadoResponse;
import com.example.minimal_prod_backend.service.TarifaEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TarifaEmpleadoController {

    private final TarifaEmpleadoService tarifaEmpleadoService;

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('TARIFAS_EMPLEADO_TAG', 'READ')")
    public List<TarifaEmpleadoResponse> tarifasEmpleado() {
        return tarifaEmpleadoService.getTarifasEmpleado();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('TARIFAS_EMPLEADO_TAG', 'READ')")
    public TarifaEmpleadoResponse tarifaEmpleado(@Argument Long id) {
        return tarifaEmpleadoService.getTarifaEmpleadoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TARIFAS_EMPLEADO_TAG', 'WRITE')")
    public TarifaEmpleadoResponse createTarifaEmpleado(@Argument("input") TarifaEmpleadoRequest request) {
        return tarifaEmpleadoService.createTarifaEmpleado(request);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TARIFAS_EMPLEADO_TAG', 'WRITE')")
    public TarifaEmpleadoResponse updateTarifaEmpleado(@Argument Long id, @Argument("input") TarifaEmpleadoRequest request) {
        return tarifaEmpleadoService.updateTarifaEmpleado(id, request);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('TARIFAS_EMPLEADO_TAG', 'DELETE')")
    public boolean deleteTarifaEmpleado(@Argument Long id) {
        tarifaEmpleadoService.deleteTarifaEmpleado(id);
        return true;
    }
}
