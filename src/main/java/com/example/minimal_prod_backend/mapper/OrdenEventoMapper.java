package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrdenEventoMapper {

    @Mapping(source = "orden", target = "orden")
    OrdenEventoResponse toResponse(OrdenEvento entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orden", ignore = true)
    OrdenEvento toEntity(OrdenEventoInput dto);

    @Mapping(target = "orden", ignore = true)
    void updateEntityFromInput(OrdenEventoInput dto, @MappingTarget OrdenEvento entity);

    @Mapping(source = "id", target = "id")
    OrdenProduccionResponse toOrdenProduccionResponse(OrdenProduccion orden);
}
