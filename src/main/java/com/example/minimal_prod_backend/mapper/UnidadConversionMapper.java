package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.UnidadConversionInput;
import com.example.minimal_prod_backend.dto.UnidadConversionResponse;
import com.example.minimal_prod_backend.entity.UnidadConversion;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadConversionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "origenId", target = "origen.id"),
            @Mapping(source = "destinoId", target = "destino.id")
    })
    UnidadConversion toEntity(UnidadConversionInput input);

    List<UnidadConversionResponse> toResponseList(List<UnidadConversion> conteos);

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