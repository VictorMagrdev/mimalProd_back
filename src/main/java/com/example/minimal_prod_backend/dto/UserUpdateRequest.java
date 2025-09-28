package com.example.minimal_prod_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    private String email;
    private String telefono;
    private String nombre;
    private String apellidos;
    private Long centroCostoId;
    private Double capacidadHorasDia;
    private Boolean activo;
    private Set<Long> roleIds;
}
