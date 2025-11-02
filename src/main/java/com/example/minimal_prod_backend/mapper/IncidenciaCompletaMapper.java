package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.IncidenciaConArchivosRequest;
import com.example.minimal_prod_backend.dto.IncidenciaResponse;
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
            @Mapping(target = "tipoIncidencia", source = "tipoIncidencia.codigo"),
            @Mapping(target = "estado", source = "estado.nombre"),
    })
    IncidenciaResponse toResponse(Incidencia incidencia);
}