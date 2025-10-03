package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.ProductoRequest;
import com.example.minimal_prod_backend.dto.ProductoResponse;
import com.example.minimal_prod_backend.entity.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "metodoValoracionId", target = "metodoValoracion.id"),
            @Mapping(source = "tipoId", target = "tipo.id"),
            @Mapping(source = "unidadBaseId", target = "unidadBase.id")
    })
    Producto toEntity(ProductoRequest input);

    List<ProductoResponse> toResponseList(List<Producto> conteos);

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
    void updateEntityFromInput(ProductoRequest input, @MappingTarget Producto entity);
}