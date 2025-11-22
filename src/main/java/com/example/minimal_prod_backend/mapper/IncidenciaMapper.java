package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.IncidenciaRequest;
import com.example.minimal_prod_backend.dto.Response.IncidenciaResponse;
import com.example.minimal_prod_backend.entity.Incidencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncidenciaMapper {

    @Mapping(source = "tipoIncidencia.id", target = "tipoIncidenciaId")
    @Mapping(source = "estado.id", target = "estadoId")
    @Mapping(source = "maquina.id", target = "maquinaId")
    @Mapping(source = "orden.id", target = "ordenId")
    @Mapping(source = "estacion.id", target = "estacionId")
    @Mapping(source = "reportadoPor.id", target = "reportadoPorId")
    @Mapping(source = "asignadoA.id", target = "asignadoAId")
    IncidenciaResponse toResponse(Incidencia entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoIncidencia", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "maquina", ignore = true)
    @Mapping(target = "orden", ignore = true)
    @Mapping(target = "estacion", ignore = true)
    @Mapping(target = "reportadoPor", ignore = true)
    @Mapping(target = "asignadoA", ignore = true)
    Incidencia toEntity(IncidenciaRequest dto);
}

