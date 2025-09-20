package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.ProductoInput;
import com.example.minimal_prod_backend.dto.ProductoResponse;
import com.example.minimal_prod_backend.entity.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoResponse toResponse(Producto entity);

    List<ProductoResponse> toResponseList(List<Producto> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "unidadBase", ignore = true)
    Producto toEntity(ProductoInput input);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "unidadBase", ignore = true)
    void updateEntityFromInput(ProductoInput input, @MappingTarget Producto entity);
}
