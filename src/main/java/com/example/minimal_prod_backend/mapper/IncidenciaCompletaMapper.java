package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.IncidenciaConArchivosRequest;
import com.example.minimal_prod_backend.dto.Response.IncidenciaResponse;
import com.example.minimal_prod_backend.entity.Incidencia;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IncidenciaCompletaMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "tipoIncidencia", ignore = true),
            @Mapping(target = "estado", ignore = true),
            @Mapping(target = "maquina", ignore = true),
            @Mapping(target = "orden", ignore = true),
            @Mapping(target = "estacion", ignore = true),
            @Mapping(target = "reportadoPor", ignore = true),
            @Mapping(target = "asignadoA", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    Incidencia toEntity(IncidenciaConArchivosRequest request);

    @Mappings({
            @Mapping(target = "tipoIncidenciaId", source = "tipoIncidencia.codigo"),
            @Mapping(target = "estadoId", source = "estado.nombre"),
    })
    IncidenciaResponse toResponse(Incidencia incidencia);
}