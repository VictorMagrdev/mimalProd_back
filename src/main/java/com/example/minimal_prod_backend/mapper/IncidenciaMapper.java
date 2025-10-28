package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.Incidencia;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IncidenciaMapper {

    @Mapping(source = "tipoIncidencia.nombre", target = "tipoIncidencia")
    @Mapping(source = "estado.nombre", target = "estado")
    IncidenciaResponse toResponse(Incidencia entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoIncidencia", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    Incidencia toEntity(IncidenciaRequest dto);
}
