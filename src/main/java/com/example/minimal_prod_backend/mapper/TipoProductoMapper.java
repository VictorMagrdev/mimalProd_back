package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoProductoInput;
import com.example.minimal_prod_backend.dto.TipoProductoResponse;
import com.example.minimal_prod_backend.entity.TipoProducto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TipoProductoMapper {

    TipoProductoResponse toResponse(TipoProducto entity);

    TipoProducto toEntity(TipoProductoInput dto);

    void updateEntityFromInput(TipoProductoInput dto, @MappingTarget TipoProducto entity);
}
