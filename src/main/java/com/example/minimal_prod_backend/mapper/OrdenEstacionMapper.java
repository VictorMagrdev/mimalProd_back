package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.OrdenEstacionRequest;
import com.example.minimal_prod_backend.dto.Response.OrdenEstacionResponse;
import com.example.minimal_prod_backend.entity.OrdenEstacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrdenEstacionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id"),
            @Mapping(source = "estacionId", target = "estacion.id"),
            @Mapping(source = "estadoOrdenEstacionId", target = "estado.id")
    })
    OrdenEstacion toEntity(OrdenEstacionRequest input);

    @Mappings({
            @Mapping(source = "orden.id", target = "ordenId"),
            @Mapping(source = "estacion.id", target = "estacionId"),
            @Mapping(source = "estado.id", target = "estadoOrdenEstacionId")
    })
    OrdenEstacionResponse toResponse(OrdenEstacion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id"),
            @Mapping(source = "estacionId", target = "estacion.id"),
            @Mapping(source = "estadoOrdenEstacionId", target = "estado.id")
    })
    void updateEntityFromInput(OrdenEstacionRequest input, @MappingTarget OrdenEstacion entity);
}