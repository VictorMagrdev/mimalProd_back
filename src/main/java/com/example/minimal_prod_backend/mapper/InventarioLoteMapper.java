package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.InventarioLoteInput;
import com.example.minimal_prod_backend.dto.InventarioLoteResponse;
import com.example.minimal_prod_backend.entity.InventarioLote;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InventarioLoteMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "actualizadoEn", ignore = true),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "loteId", target = "lote.id"),
            @Mapping(source = "bodegaId", target = "bodega.id"),
            @Mapping(source = "unidadId", target = "unidad.id")
    })
    InventarioLote toEntity(InventarioLoteInput input);

    @Mappings({
            @Mapping(source = "producto.id", target = "productoId"),
            @Mapping(source = "lote.id", target = "loteId"),
            @Mapping(source = "bodega.id", target = "bodegaId"),
            @Mapping(source = "unidad.id", target = "unidadId")
    })
    InventarioLoteResponse toResponse(InventarioLote entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "actualizadoEn", ignore = true),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "loteId", target = "lote.id"),
            @Mapping(source = "bodegaId", target = "bodega.id"),
            @Mapping(source = "unidadId", target = "unidad.id")
    })
    void updateEntityFromInput(InventarioLoteInput input, @MappingTarget InventarioLote entity);
}
