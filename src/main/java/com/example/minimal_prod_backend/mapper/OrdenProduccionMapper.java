package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.OrdenProduccionRequest;
import com.example.minimal_prod_backend.dto.Response.OrdenProduccionResponse;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdenProduccionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "unidadId", target = "unidad.id"),
            @Mapping(source = "estadoId", target = "estado.id"),
            @Mapping(target = "creadoEn", ignore = true),
            @Mapping(target = "actualizadoEn", ignore = true),
            @Mapping(target = "lotes", ignore = true)
    })
    OrdenProduccion toEntity(OrdenProduccionRequest input);

    @Mappings({
            @Mapping(source = "unidad.id", target = "unidadId"),
            @Mapping(source = "estado.id", target = "estadoId"),
            @Mapping(source = "creadoPor.id", target = "creadoPor")
    })
    OrdenProduccionResponse toResponse(OrdenProduccion entity);

    List<OrdenProduccionResponse> toResponseList(List<OrdenProduccion> ordenes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "lotes", ignore = true),
            @Mapping(source = "unidadId", target = "unidad.id"),
            @Mapping(source = "estadoId", target = "estado.id"),
            @Mapping(target = "creadoEn", ignore = true),
            @Mapping(target = "actualizadoEn", ignore = true)
    })
    void updateEntityFromInput(OrdenProduccionRequest input, @MappingTarget OrdenProduccion entity);
}
