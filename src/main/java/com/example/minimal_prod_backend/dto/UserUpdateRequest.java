package com.example.minimal_prod_backend.dto;

import java.util.Set;

public record UserUpdateRequest(
        String email,
        String telefono,
        String nombre,
        String apellidos,
        Long centroCostoId,
        Double capacidadHorasDia,
        Boolean activo,
        Set<Long> roleIds
) {
}