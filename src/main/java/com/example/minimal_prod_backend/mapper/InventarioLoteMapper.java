package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.InventarioLoteRequest;
import com.example.minimal_prod_backend.dto.InventarioLoteResponse;
import com.example.minimal_prod_backend.entity.InventarioLote;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InventarioLoteMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "actualizadoEn", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "lote", ignore = true),
            @Mapping(target = "bodega", ignore = true),
            @Mapping(target = "unidad", ignore = true)
    })
    InventarioLote toEntity(InventarioLoteRequest input);

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
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "lote", ignore = true),
            @Mapping(target = "bodega", ignore = true),
            @Mapping(target = "unidad", ignore = true)
    })
    void updateEntityFromInput(InventarioLoteRequest input, @MappingTarget InventarioLote entity);
}
