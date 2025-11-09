package com.example.minimal_prod_backend.dto.Request;

import java.time.Duration;
import java.util.Set;

public record UserUpdateRequest(
        String email,
        String telefono,
        String nombre,
        String apellidos,
        Long centroCostoId,
        Duration capacidadHorasDia,
        Boolean activo,
        Set<Long> roleIds
) {
}