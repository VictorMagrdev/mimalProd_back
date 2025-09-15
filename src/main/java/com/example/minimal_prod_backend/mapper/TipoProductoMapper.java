package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoProductoResponse;
import com.example.minimal_prod_backend.entity.TipoProducto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoProductoMapper {
    TipoProductoResponse toResponse(TipoProducto tipoProducto);
}
