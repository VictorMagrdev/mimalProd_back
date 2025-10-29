package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.RutaProduccionRequest;
import com.example.minimal_prod_backend.dto.RutaProduccionResponse;
import com.example.minimal_prod_backend.entity.RutaProduccion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RutaProduccionMapper {

    RutaProduccionResponse toResponse(RutaProduccion rutaProduccion);


    RutaProduccion toEntity(RutaProduccionRequest rutaProduccionRequest);
}
