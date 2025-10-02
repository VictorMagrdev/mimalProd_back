package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OrdenProduccionRequest;
import com.example.minimal_prod_backend.dto.OrdenProduccionResponse;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdenProduccionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "unidadId", target = "unidad.id"),
            @Mapping(source = "estadoId", target = "estado.id"),
            @Mapping(source = "creadoPor", target = "creadoPor.id")
    })
    OrdenProduccion toEntity(OrdenProduccionRequest input);

    List<OrdenProduccionResponse> toResponseList(List<OrdenProduccion> conteos);

    @Mappings({
            @Mapping(source = "unidad.id", target = "unidadId"),
            @Mapping(source = "estado.id", target = "estadoId"),
            @Mapping(source = "creadoPor.id", target = "creadoPor")
    })
    OrdenProduccionResponse toResponse(OrdenProduccion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "lotes", ignore = true),
            @Mapping(source = "unidadId", target = "unidad.id"),
            @Mapping(source = "estadoId", target = "estado.id"),
            @Mapping(source = "creadoPor", target = "creadoPor.id")
    })
    void updateEntityFromInput(OrdenProduccionRequest input, @MappingTarget OrdenProduccion entity);
}