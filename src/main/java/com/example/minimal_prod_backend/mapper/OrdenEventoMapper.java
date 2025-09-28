package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OrdenEventoInput;
import com.example.minimal_prod_backend.dto.OrdenEventoResponse;
import com.example.minimal_prod_backend.entity.OrdenEvento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OrdenEventoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id")
    })
    OrdenEvento toEntity(OrdenEventoInput input);

    @Mappings({
        @Mapping(source = "orden.id", target = "ordenId")
    })
    OrdenEventoResponse toResponse(OrdenEvento entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id")
    })
    void updateEntityFromInput(OrdenEventoInput input, @MappingTarget OrdenEvento entity);
}