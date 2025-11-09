package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.PuntoReordenRequest;
import com.example.minimal_prod_backend.dto.Response.PuntoReordenResponse;
import com.example.minimal_prod_backend.entity.PuntoReorden;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PuntoReordenMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "unidadId", target = "unidad.id")
    })
    PuntoReorden toEntity(PuntoReordenRequest input);

    List<PuntoReordenResponse> toResponseList(List<PuntoReorden> conteos);

    @Mappings({
            @Mapping(source = "producto.id", target = "productoId"),
            @Mapping(source = "unidad.id", target = "unidadId")
    })
    PuntoReordenResponse toResponse(PuntoReorden entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "unidadId", target = "unidad.id")
    })
    void updateEntityFromInput(PuntoReordenRequest input, @MappingTarget PuntoReorden entity);
}