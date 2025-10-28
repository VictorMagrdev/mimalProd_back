package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstructuraProductoRequest;
import com.example.minimal_prod_backend.dto.EstructuraProductoResponse;
import com.example.minimal_prod_backend.entity.EstructuraProducto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstructuraProductoMapper {

    @Mappings({
            @Mapping(target = "productoPadre.id", source = "productoPadreId"),
            @Mapping(target = "productoHijo.id", source = "productoHijoId"),
            @Mapping(target = "unidad.id", source = "unidadId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    EstructuraProducto toEntity(EstructuraProductoRequest request);

    @Mappings({
            @Mapping(target = "productoPadreId", source = "productoPadre.id"),
            @Mapping(target = "productoPadreCodigo", source = "productoPadre.codigo"),
            @Mapping(target = "productoPadreNombre", source = "productoPadre.nombre"),
            @Mapping(target = "productoHijoId", source = "productoHijo.id"),
            @Mapping(target = "productoHijoCodigo", source = "productoHijo.codigo"),
            @Mapping(target = "productoHijoNombre", source = "productoHijo.nombre"),
            @Mapping(target = "unidadId", source = "unidad.id"),
            @Mapping(target = "unidadNombre", source = "unidad.nombre")
    })
    EstructuraProductoResponse toResponse(EstructuraProducto estructura);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "productoPadre.id", source = "productoPadreId"),
            @Mapping(target = "productoHijo.id", source = "productoHijoId"),
            @Mapping(target = "unidad.id", source = "unidadId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    void updateEntityFromRequest(EstructuraProductoRequest request, @MappingTarget EstructuraProducto entity);
}