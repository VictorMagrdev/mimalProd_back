package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OperacionOrdenEstadoRequest;
import com.example.minimal_prod_backend.dto.OperacionOrdenEstadoResponse;
import com.example.minimal_prod_backend.entity.OperacionOrdenEstado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperacionOrdenEstadoMapper {

    OperacionOrdenEstadoResponse toResponse(OperacionOrdenEstado operacionOrdenEstado);

    OperacionOrdenEstado toEntity(OperacionOrdenEstadoRequest operacionOrdenEstadoRequest);
}
