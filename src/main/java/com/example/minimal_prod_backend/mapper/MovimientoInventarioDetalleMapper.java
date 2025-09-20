package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MovimientoInventarioDetalleMapper {

    @Mapping(source = "movimiento.id", target = "idMovimiento")
    @Mapping(source = "producto", target = "producto")
    @Mapping(source = "lote", target = "lote")
    @Mapping(source = "unidad", target = "unidad")
    MovimientoInventarioDetalleResponse toResponse(MovimientoInventarioDetalle entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movimiento", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "lote", ignore = true)
    @Mapping(target = "unidad", ignore = true)
    MovimientoInventarioDetalle toEntity(MovimientoInventarioDetalleInput dto);

    @Mapping(target = "movimiento", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "lote", ignore = true)
    @Mapping(target = "unidad", ignore = true)
    void updateEntityFromInput(MovimientoInventarioDetalleInput dto,
                               @MappingTarget MovimientoInventarioDetalle entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    ProductoResponse toProductoResponse(Producto producto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "numeroLote", target = "numeroLote")
    LoteProduccionResponse toLoteProduccionResponse(LoteProduccion lote);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    UnidadMedidaResponse toUnidadMedidaResponse(UnidadMedida unidad);
}
