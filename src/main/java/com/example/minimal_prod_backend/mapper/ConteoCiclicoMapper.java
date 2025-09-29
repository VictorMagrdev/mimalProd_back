package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.ConteoCiclicoInput;
import com.example.minimal_prod_backend.dto.ConteoCiclicoResponse;
import com.example.minimal_prod_backend.entity.ConteoCiclico;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConteoCiclicoMapper {

    @Mappings({
            @Mapping(target = "producto.id", source = "productoId"),
            @Mapping(target = "bodega.id", source = "bodegaId"),
            @Mapping(target = "lote.id", source = "loteId"),
            @Mapping(target = "unidad.id", source = "unidadId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "fecha", source = "fecha")
    })
    ConteoCiclico toEntity(ConteoCiclicoInput input);

    @Mappings({
            @Mapping(target = "productoId", source = "producto.id"),
            @Mapping(target = "bodegaId", source = "bodega.id"),
            @Mapping(target = "loteId", source = "lote.id"),
            @Mapping(target = "unidadId", source = "unidad.id"),
            @Mapping(target = "fecha", source = "fecha")
    })
    ConteoCiclicoResponse toResponse(ConteoCiclico conteoCiclico);

    List<ConteoCiclicoResponse> toResponseList(List<ConteoCiclico> conteos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "producto.id", source = "productoId"),
            @Mapping(target = "bodega.id", source = "bodegaId"),
            @Mapping(target = "lote.id", source = "loteId"),
            @Mapping(target = "unidad.id", source = "unidadId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "fecha", source = "fecha")
    })
    void updateEntityFromInput(ConteoCiclicoInput input, @MappingTarget ConteoCiclico entity);
}
