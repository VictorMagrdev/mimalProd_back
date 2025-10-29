package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstadoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.EstadoIncidenciaResponse;
import com.example.minimal_prod_backend.entity.EstadoIncidencia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadoIncidenciaMapper {
    EstadoIncidencia toEntity(EstadoIncidenciaRequest dto);

    EstadoIncidenciaResponse toResponse(EstadoIncidencia entity);
}

