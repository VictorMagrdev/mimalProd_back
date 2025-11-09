package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.LineaOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.LineaOrdenResponse;
import com.example.minimal_prod_backend.entity.LineaOrden;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LineaOrdenMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true),
            @Mapping(target = "costoTotal", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id"),
            @Mapping(source = "productoComponenteId", target = "productoComponente.id"),
            @Mapping(source = "unidadComponenteId", target = "unidadComponente.id")
    })
    LineaOrden toEntity(LineaOrdenRequest input);

    List<LineaOrdenResponse> toResponseList(List<LineaOrden> conteos);

    @Mappings({
            @Mapping(source = "orden.id", target = "ordenId"),
            @Mapping(source = "productoComponente.id", target = "productoComponenteId"),
            @Mapping(source = "unidadComponente.id", target = "unidadComponenteId")
    })
    LineaOrdenResponse toResponse(LineaOrden entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true),
            @Mapping(target = "costoTotal", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id"),
            @Mapping(source = "productoComponenteId", target = "productoComponente.id"),
            @Mapping(source = "unidadComponenteId", target = "unidadComponente.id")
    })
    void updateEntityFromInput(LineaOrdenRequest input, @MappingTarget LineaOrden entity);
}