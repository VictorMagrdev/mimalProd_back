package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OrdenEstacionInput;
import com.example.minimal_prod_backend.dto.OrdenEstacionResponse;
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
    OrdenEstacion toEntity(OrdenEstacionInput input);

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
    void updateEntityFromInput(OrdenEstacionInput input, @MappingTarget OrdenEstacion entity);
}