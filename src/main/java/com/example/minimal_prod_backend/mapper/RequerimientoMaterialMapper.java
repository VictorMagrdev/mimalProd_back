package com.example.minimal_prod_backend.mapper;


import com.example.minimal_prod_backend.dto.Request.RequerimientoMaterialRequest;
import com.example.minimal_prod_backend.dto.Response.RequerimientoMaterialResponse;
import com.example.minimal_prod_backend.entity.RequerimientoMaterial;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RequerimientoMaterialMapper {

    @Mappings({
            @Mapping(target = "producto.id", source = "productoId"),
            @Mapping(target = "ordenProduccion.id", source = "ordenProduccionId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true),
            @Mapping(target = "cantidadAPedir", ignore = true)
    })
    RequerimientoMaterial toEntity(RequerimientoMaterialRequest request);

    @Mappings({
            @Mapping(target = "productoId", source = "producto.id"),
            @Mapping(target = "productoCodigo", source = "producto.codigo"),
            @Mapping(target = "productoNombre", source = "producto.nombre"),
            @Mapping(target = "ordenProduccionId", source = "ordenProduccion.id"),
            @Mapping(target = "ordenProduccionNumero", source = "ordenProduccion.numeroOrden")
    })
    RequerimientoMaterialResponse toResponse(RequerimientoMaterial requerimiento);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "producto.id", source = "productoId"),
            @Mapping(target = "ordenProduccion.id", source = "ordenProduccionId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true),
            @Mapping(target = "cantidadAPedir", ignore = true)
    })
    void updateEntityFromRequest(RequerimientoMaterialRequest request, @MappingTarget RequerimientoMaterial entity);
}