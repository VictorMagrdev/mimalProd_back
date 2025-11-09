package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.MovimientoInventarioDetalleRequest;
import com.example.minimal_prod_backend.dto.Response.MovimientoInventarioDetalleResponse;
import com.example.minimal_prod_backend.entity.MovimientoInventarioDetalle;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MovimientoInventarioDetalleMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "movimiento", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "lote", ignore = true),
            @Mapping(target = "unidad", ignore = true)
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
            @Mapping(target = "movimiento", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "lote", ignore = true),
            @Mapping(target = "unidad", ignore = true)
    })
    void updateEntityFromInput(MovimientoInventarioDetalleRequest input, @MappingTarget MovimientoInventarioDetalle entity);
}
