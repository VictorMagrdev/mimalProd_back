package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.OperacionOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.OperacionOrdenResponse;
import com.example.minimal_prod_backend.entity.OperacionOrden;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OperacionOrdenMapper {

    @Mapping(target = "operacionRutaId", source = "operacionRuta.id")
    @Mapping(target = "estadoId", source = "estado.id")
    OperacionOrdenResponse toResponse(OperacionOrden operacionOrden);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "operacionRuta", ignore = true)
    @Mapping(target = "estado", ignore = true)
    OperacionOrden toEntity(OperacionOrdenRequest operacionOrdenRequest);
}


