package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OrdenEventoRequest;
import com.example.minimal_prod_backend.dto.OrdenEventoResponse;
import com.example.minimal_prod_backend.entity.OrdenEvento;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrdenEventoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id")
    })
    OrdenEvento toEntity(OrdenEventoRequest input);

    @Mappings({
            @Mapping(source = "orden.id", target = "ordenId")
    })
    OrdenEventoResponse toResponse(OrdenEvento entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id")
    })
    void updateEntityFromInput(OrdenEventoRequest input, @MappingTarget OrdenEvento entity);
}