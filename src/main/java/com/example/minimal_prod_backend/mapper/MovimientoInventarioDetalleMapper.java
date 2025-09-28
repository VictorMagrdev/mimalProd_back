package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.MovimientoInventarioDetalleInput;
import com.example.minimal_prod_backend.dto.MovimientoInventarioDetalleResponse;
import com.example.minimal_prod_backend.entity.MovimientoInventarioDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MovimientoInventarioDetalleMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "movimientoId", target = "movimiento.id"),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "loteId", target = "lote.id"),
            @Mapping(source = "unidadId", target = "unidad.id")
    })
    MovimientoInventarioDetalle toEntity(MovimientoInventarioDetalleInput input);

    @Mappings({
        @Mapping(source = "movimiento.id", target = "movimientoId"),
        @Mapping(source = "producto.id", target = "productoId"),
        @Mapping(source = "lote.id", target = "loteId"),
        @Mapping(source = "unidad.id", target = "unidadId")
    })
    MovimientoInventarioDetalleResponse toResponse(MovimientoInventarioDetalle entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "movimientoId", target = "movimiento.id"),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "loteId", target = "lote.id"),
            @Mapping(source = "unidadId", target = "unidad.id")
    })
    void updateEntityFromInput(MovimientoInventarioDetalleInput input, @MappingTarget MovimientoInventarioDetalle entity);
}
