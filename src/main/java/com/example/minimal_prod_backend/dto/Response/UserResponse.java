package com.example.minimal_prod_backend.dto.Response;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Set;

public record UserResponse(
        Long id,
        String username,
        String email,
        String telefono,
        String codigoEmpleado,
        String nombre,
        String apellidos,
        Long centroCostoId,
        Duration capacidadHorasDia,
        Boolean activo,
        OffsetDateTime creadoEn,
        OffsetDateTime actualizadoEn,
        Set<RolResponse> roles
) {
}