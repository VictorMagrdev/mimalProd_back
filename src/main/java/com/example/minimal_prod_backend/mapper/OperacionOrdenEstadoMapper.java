package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OperacionOrdenEstadoRequest;
import com.example.minimal_prod_backend.dto.OperacionOrdenEstadoResponse;
import com.example.minimal_prod_backend.entity.OperacionOrdenEstado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OperacionOrdenEstadoMapper {

    OperacionOrdenEstadoResponse toResponse(OperacionOrdenEstado operacionOrdenEstado);

    OperacionOrdenEstado toEntity(OperacionOrdenEstadoRequest operacionOrdenEstadoRequest);
}
