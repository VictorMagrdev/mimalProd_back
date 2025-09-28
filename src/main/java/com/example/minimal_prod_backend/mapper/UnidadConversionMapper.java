package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.UnidadConversionInput;
import com.example.minimal_prod_backend.dto.UnidadConversionResponse;
import com.example.minimal_prod_backend.entity.UnidadConversion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UnidadConversionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "origenId", target = "origen.id"),
            @Mapping(source = "destinoId", target = "destino.id")
    })
    UnidadConversion toEntity(UnidadConversionInput input);

    @Mappings({
        @Mapping(source = "origen.id", target = "origenId"),
        @Mapping(source = "destino.id", target = "destinoId")
    })
    UnidadConversionResponse toResponse(UnidadConversion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "origenId", target = "origen.id"),
            @Mapping(source = "destinoId", target = "destino.id")
    })
    void updateEntityFromInput(UnidadConversionInput input, @MappingTarget UnidadConversion entity);
}