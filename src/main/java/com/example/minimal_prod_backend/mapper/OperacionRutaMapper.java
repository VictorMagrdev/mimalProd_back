package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OperacionRutaRequest;
import com.example.minimal_prod_backend.dto.OperacionRutaResponse;
import com.example.minimal_prod_backend.entity.OperacionRuta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OperacionRutaMapper {

    OperacionRutaResponse toResponse(OperacionRuta operacionRuta);

    @Mapping(target = "ruta", ignore = true)
    OperacionRuta toEntity(OperacionRutaRequest operacionRutaRequest);
}
