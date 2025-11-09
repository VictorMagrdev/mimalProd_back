package com.example.minimal_prod_backend.dto.Response;

import com.example.minimal_prod_backend.entity.TipoPrioridad;

public record TipoIncidenciaResponse(Long id, String codigo, String nombre, String descripcion,
                                     TipoPrioridad prioridadBase) {
}
