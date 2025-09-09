package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.entity.CostoOrden;
import com.example.minimal_prod_backend.service.CostoOrdenService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CostoOrdenController {

    private final CostoOrdenService costoOrdenService;

    public CostoOrdenController(CostoOrdenService costoOrdenService) {
        this.costoOrdenService = costoOrdenService;
    }

    @QueryMapping
    @PreAuthorize("hasPermission('COSTO_ORDEN', 'READ')")
    public List<CostoOrden> costosOrden() {
        return costoOrdenService.getCostosOrden();
    }

    @QueryMapping
    @PreAuthorize("hasPermission('COSTO_ORDEN', 'READ')")
    public CostoOrden costoOrden(@Argument Long id) {
        return costoOrdenService.getCostoOrdenById(id);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('COSTO_ORDEN', 'CREATE')")
    public CostoOrden createCostoOrden(@Argument CostoOrden costoOrden) {
        return costoOrdenService.createCostoOrden(costoOrden);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('COSTO_ORDEN', 'UPDATE')")
    public CostoOrden updateCostoOrden(@Argument Long id, @Argument CostoOrden costoOrden) {
        return costoOrdenService.updateCostoOrden(id, costoOrden);
    }

    @MutationMapping
    @PreAuthorize("hasPermission('COSTO_ORDEN', 'DELETE')")
    public boolean deleteCostoOrden(@Argument Long id) {
        costoOrdenService.deleteCostoOrden(id);
        return true;
    }
}
