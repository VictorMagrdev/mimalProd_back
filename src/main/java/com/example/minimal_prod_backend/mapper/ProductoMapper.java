package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.ProductoInput;
import com.example.minimal_prod_backend.dto.ProductoResponse;
import com.example.minimal_prod_backend.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "metodoValoracionId", target = "metodoValoracion.id"),
            @Mapping(source = "tipoId", target = "tipo.id"),
            @Mapping(source = "unidadBaseId", target = "unidadBase.id")
    })
    Producto toEntity(ProductoInput input);

    @Mappings({
        @Mapping(source = "metodoValoracion.id", target = "metodoValoracionId"),
        @Mapping(source = "tipo.id", target = "tipoId"),
        @Mapping(source = "unidadBase.id", target = "unidadBaseId")
    })
    ProductoResponse toResponse(Producto entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "metodoValoracionId", target = "metodoValoracion.id"),
            @Mapping(source = "tipoId", target = "tipo.id"),
            @Mapping(source = "unidadBaseId", target = "unidadBase.id")
    })
    void updateEntityFromInput(ProductoInput input, @MappingTarget Producto entity);
}