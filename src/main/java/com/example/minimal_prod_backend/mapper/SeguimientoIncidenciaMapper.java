package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.SeguimientoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.Response.SeguimientoIncidenciaResponse;
import com.example.minimal_prod_backend.entity.SeguimientoIncidencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeguimientoIncidenciaMapper {

    @Mapping(source = "incidencia.codigo", target = "incidenciaCodigo")
    @Mapping(source = "estadoAnterior.nombre", target = "estadoAnterior")
    @Mapping(source = "estadoNuevo.nombre", target = "estadoNuevo")
    SeguimientoIncidenciaResponse toResponse(SeguimientoIncidencia entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "incidencia", ignore = true)
    @Mapping(target = "estadoAnterior", ignore = true)
    @Mapping(target = "estadoNuevo", ignore = true)
    @Mapping(target = "realizadoEn", ignore = true)
    SeguimientoIncidencia toEntity(SeguimientoIncidenciaRequest dto);
}
