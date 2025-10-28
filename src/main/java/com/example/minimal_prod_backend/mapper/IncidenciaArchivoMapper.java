package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.IncidenciaArchivo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IncidenciaArchivoMapper {

    @Mapping(target = "incidenciaId", source = "incidencia.id")
    IncidenciaArchivoResponse toResponse(IncidenciaArchivo entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subidoEn", ignore = true)
    @Mapping(target = "incidencia", ignore = true) // si viene solo incidenciaId en DTO
    IncidenciaArchivo toEntity(IncidenciaArchivoRequest dto);
}

