package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.TipoProductoRequest;
import com.example.minimal_prod_backend.dto.Response.TipoProductoResponse;
import com.example.minimal_prod_backend.entity.TipoProducto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoProductoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    TipoProducto toEntity(TipoProductoRequest input);

    TipoProductoResponse toResponse(TipoProducto entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(TipoProductoRequest input, @MappingTarget TipoProducto entity);
}