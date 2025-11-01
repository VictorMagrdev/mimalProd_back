package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.IncidenciaArchivoRequest;
import com.example.minimal_prod_backend.dto.IncidenciaArchivoResponse;
import com.example.minimal_prod_backend.entity.IncidenciaArchivo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncidenciaArchivoMapper {

    @Mapping(target = "incidenciaId", source = "incidencia.id")
    IncidenciaArchivoResponse toResponse(IncidenciaArchivo entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subidoEn", ignore = true)
    @Mapping(target = "incidencia", ignore = true)
    IncidenciaArchivo toEntity(IncidenciaArchivoRequest dto);
}

