package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.LineaOrdenInput;
import com.example.minimal_prod_backend.dto.LineaOrdenResponse;
import com.example.minimal_prod_backend.entity.LineaOrden;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

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
    LineaOrden toEntity(LineaOrdenInput input);
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
    void updateEntityFromInput(LineaOrdenInput input, @MappingTarget LineaOrden entity);
}