package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.OperacionRutaRequest;
import com.example.minimal_prod_backend.dto.Response.OperacionRutaResponse;
import com.example.minimal_prod_backend.entity.OperacionRuta;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OperacionRutaMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "rutaId", target = "ruta.id"),
            @Mapping(source = "estacionId", target = "estacionId"),
            @Mapping(target = "creadoEn", ignore = true)
    })
    OperacionRuta toEntity(OperacionRutaRequest input);

    @Mappings({
            @Mapping(source = "ruta.id", target = "rutaId"),
            @Mapping(source = "estacionId", target = "estacionId")
    })
    OperacionRutaResponse toResponse(OperacionRuta entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "rutaId", target = "ruta.id"),
            @Mapping(source = "estacionId", target = "estacionId"),
            @Mapping(target = "creadoEn", ignore = true)
    })
    void updateEntityFromInput(OperacionRutaRequest input, @MappingTarget OperacionRuta entity);
}
