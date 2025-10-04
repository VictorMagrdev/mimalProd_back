package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OperacionOrdenRequest;
import com.example.minimal_prod_backend.dto.OperacionOrdenResponse;
import com.example.minimal_prod_backend.entity.OperacionOrden;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OperacionOrdenMapper {

    OperacionOrdenResponse toResponse(OperacionOrden operacionOrden);

    @Mapping(target = "operacionRuta", ignore = true)
    @Mapping(target = "estado", ignore = true)
    OperacionOrden toEntity(OperacionOrdenRequest operacionOrdenRequest);
}
