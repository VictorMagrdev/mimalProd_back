package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.UnidadConversionRequest;
import com.example.minimal_prod_backend.dto.Response.UnidadConversionResponse;
import com.example.minimal_prod_backend.entity.UnidadConversion;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadConversionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "origen", ignore = true),
            @Mapping(target = "destino", ignore = true)
    })
    UnidadConversion toEntity(UnidadConversionRequest input);

    List<UnidadConversionResponse> toResponseList(List<UnidadConversion> conteos);

    @Mappings({
            @Mapping(source = "origen.id", target = "origenId"),
            @Mapping(source = "destino.id", target = "destinoId")
    })
    UnidadConversionResponse toResponse(UnidadConversion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "origen", ignore = true),
            @Mapping(target = "destino", ignore = true)
    })
    void updateEntityFromInput(UnidadConversionRequest input, @MappingTarget UnidadConversion entity);
}
