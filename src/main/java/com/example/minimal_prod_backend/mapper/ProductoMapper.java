package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.ProductoRequest;
import com.example.minimal_prod_backend.dto.Response.ProductoResponse;
import com.example.minimal_prod_backend.entity.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "metodoValoracion", ignore = true),
            @Mapping(target = "tipo", ignore = true),
            @Mapping(target = "unidadBase", ignore = true)
    })
    Producto toEntity(ProductoRequest input);

    List<ProductoResponse> toResponseList(List<Producto> productos);

    @Mappings({
            @Mapping(source = "metodoValoracion.id", target = "metodoValoracionId"),
            @Mapping(source = "tipo.id", target = "tipoId"),
            @Mapping(source = "unidadBase.id", target = "unidadBaseId")
    })
    ProductoResponse toResponse(Producto entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "metodoValoracion", ignore = true),
            @Mapping(target = "tipo", ignore = true),
            @Mapping(target = "unidadBase", ignore = true)
    })
    void updateEntityFromInput(ProductoRequest input, @MappingTarget Producto entity);
}