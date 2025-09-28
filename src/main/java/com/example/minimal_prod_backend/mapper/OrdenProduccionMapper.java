package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OrdenProduccionInput;
import com.example.minimal_prod_backend.dto.OrdenProduccionResponse;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdenProduccionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "unidadId", target = "unidad.id"),
            @Mapping(source = "estadoId", target = "estado.id"),
            @Mapping(source = "creadoPor", target = "creadoPor.id")
    })
    OrdenProduccion toEntity(OrdenProduccionInput input);
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
    void updateEntityFromInput(OrdenProduccionInput input, @MappingTarget OrdenProduccion entity);
}