package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoProductoInput;
import com.example.minimal_prod_backend.dto.TipoProductoResponse;
import com.example.minimal_prod_backend.entity.TipoProducto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoProductoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    TipoProducto toEntity(TipoProductoInput input);

    TipoProductoResponse toResponse(TipoProducto entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(TipoProductoInput input, @MappingTarget TipoProducto entity);
}