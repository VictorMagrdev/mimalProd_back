package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrdenEstacionMapper {

    @Mapping(source = "orden", target = "orden")
    @Mapping(source = "estacion", target = "estacion")
    OrdenEstacionResponse toResponse(OrdenEstacion entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orden", ignore = true)
    @Mapping(target = "estacion", ignore = true)
    OrdenEstacion toEntity(OrdenEstacionInput dto);

    @Mapping(target = "orden", ignore = true)
    @Mapping(target = "estacion", ignore = true)
    void updateEntityFromInput(OrdenEstacionInput dto, @MappingTarget OrdenEstacion entity);

    @Mapping(source = "id", target = "id")
    OrdenProduccionResponse toOrdenProduccionResponse(OrdenProduccion orden);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    EstacionProduccionResponse toEstacionProduccionResponse(EstacionProduccion estacion);
}
