package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.MovimientoInventarioDetalleRequest;
import com.example.minimal_prod_backend.dto.MovimientoInventarioDetalleResponse;
import com.example.minimal_prod_backend.entity.MovimientoInventarioDetalle;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MovimientoInventarioDetalleMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "movimientoId", target = "movimiento.id"),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "loteId", target = "lote.id"),
            @Mapping(source = "unidadId", target = "unidad.id")
    })
    MovimientoInventarioDetalle toEntity(MovimientoInventarioDetalleRequest input);

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
    void updateEntityFromInput(MovimientoInventarioDetalleRequest input, @MappingTarget MovimientoInventarioDetalle entity);
}
