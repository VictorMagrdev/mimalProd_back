package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.EstadoIncidencia;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstadoIncidenciaMapper {
    EstadoIncidencia toEntity(EstadoIncidenciaRequest dto);
    EstadoIncidenciaResponse toResponse(EstadoIncidencia entity);
}

