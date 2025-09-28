package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.PuntoReordenInput;
import com.example.minimal_prod_backend.dto.PuntoReordenResponse;
import com.example.minimal_prod_backend.entity.PuntoReorden;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PuntoReordenMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "unidadId", target = "unidad.id")
    })
    PuntoReorden toEntity(PuntoReordenInput input);
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
    void updateEntityFromInput(PuntoReordenInput input, @MappingTarget PuntoReorden entity);
}