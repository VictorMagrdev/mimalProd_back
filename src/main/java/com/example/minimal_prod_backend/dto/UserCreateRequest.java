package com.example.minimal_prod_backend.dto;

import java.util.Set;

public record UserCreateRequest(
        String username,
        String email,
        String password,
        String telefono,
        String codigoEmpleado,
        String nombre,
        String apellidos,
        Long centroCostoId,
        Double capacidadHorasDia,
        Boolean activo,
        Set<Long> roleIds
) {}