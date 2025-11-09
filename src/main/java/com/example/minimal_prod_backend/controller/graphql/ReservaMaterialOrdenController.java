package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.ReservaMaterialOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.ReservaMaterialOrdenResponse;
import com.example.minimal_prod_backend.service.ReservaMaterialOrdenService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReservaMaterialOrdenController {

    private final ReservaMaterialOrdenService reservaMaterialOrdenService;

    public ReservaMaterialOrdenController(ReservaMaterialOrdenService reservaMaterialOrdenService) {
        this.reservaMaterialOrdenService = reservaMaterialOrdenService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('RESERVAS_MATERIAL_ORDEN_TAG', 'READ')")
    public List<ReservaMaterialOrdenResponse> reservasMaterialOrden() {
        return reservaMaterialOrdenService.getReservasMaterialOrden();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('RESERVAS_MATERIAL_ORDEN_TAG', 'READ')")
    public ReservaMaterialOrdenResponse reservaMaterialOrden(@Argument Long id) {
        return reservaMaterialOrdenService.getReservaMaterialOrdenById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('RESERVAS_MATERIAL_ORDEN_TAG', 'CREATE')")
    public ReservaMaterialOrdenResponse createReservaMaterialOrden(@Argument("input") ReservaMaterialOrdenRequest reservaMaterialOrdenInput) {
        return reservaMaterialOrdenService.createReservaMaterialOrden(reservaMaterialOrdenInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('RESERVAS_MATERIAL_ORDEN_TAG', 'UPDATE')")
    public ReservaMaterialOrdenResponse updateReservaMaterialOrden(@Argument Long id, @Argument("input") ReservaMaterialOrdenRequest reservaMaterialOrdenInput) {
        return reservaMaterialOrdenService.updateReservaMaterialOrden(id, reservaMaterialOrdenInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('RESERVAS_MATERIAL_ORDEN_TAG', 'DELETE')")
    public boolean deleteReservaMaterialOrden(@Argument Long id) {
        reservaMaterialOrdenService.deleteReservaMaterialOrden(id);
        return true;
    }
}
