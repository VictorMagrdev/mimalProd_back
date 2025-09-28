package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoProductoInput;
import com.example.minimal_prod_backend.dto.TipoProductoResponse;
import com.example.minimal_prod_backend.entity.TipoProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

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