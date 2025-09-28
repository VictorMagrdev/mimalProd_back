package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.MovimientoInventarioInput;
import com.example.minimal_prod_backend.dto.MovimientoInventarioResponse;
import com.example.minimal_prod_backend.entity.MovimientoInventario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MovimientoInventarioMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "bodegaOrigenId", target = "bodegaOrigen.id"),
            @Mapping(source = "bodegaDestinoId", target = "bodegaDestino.id"),
            @Mapping(source = "tipoMovimientoId", target = "tipoMovimiento.id"),
            @Mapping(source = "creadoPor", target = "creadoPor.id")
    })
    MovimientoInventario toEntity(MovimientoInventarioInput input);

    @Mappings({
        @Mapping(source = "bodegaOrigen.id", target = "bodegaOrigenId"),
        @Mapping(source = "bodegaDestino.id", target = "bodegaDestinoId"),
        @Mapping(source = "tipoMovimiento.id", target = "tipoMovimientoId"),
        @Mapping(source = "creadoPor.id", target = "creadoPor")
    })
    MovimientoInventarioResponse toResponse(MovimientoInventario entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "bodegaOrigenId", target = "bodegaOrigen.id"),
            @Mapping(source = "bodegaDestinoId", target = "bodegaDestino.id"),
            @Mapping(source = "tipoMovimientoId", target = "tipoMovimiento.id"),
            @Mapping(source = "creadoPor", target = "creadoPor.id")
    })
    void updateEntityFromInput(MovimientoInventarioInput input, @MappingTarget MovimientoInventario entity);
}