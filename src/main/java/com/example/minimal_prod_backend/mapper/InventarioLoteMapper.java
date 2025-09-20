package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InventarioLoteMapper {

    @Mapping(source = "producto", target = "producto")
    @Mapping(source = "lote", target = "lote")
    @Mapping(source = "bodega", target = "bodega")
    @Mapping(source = "unidad", target = "unidad")
    InventarioLoteResponse toResponse(InventarioLote entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "lote", ignore = true)
    @Mapping(target = "bodega", ignore = true)
    @Mapping(target = "unidad", ignore = true)
    @Mapping(target = "actualizadoEn", ignore = true)
    InventarioLote toEntity(InventarioLoteInput dto);

    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "lote", ignore = true)
    @Mapping(target = "bodega", ignore = true)
    @Mapping(target = "unidad", ignore = true)
    @Mapping(target = "actualizadoEn", ignore = true)
    void updateEntityFromInput(InventarioLoteInput dto, @MappingTarget InventarioLote entity);

    ProductoResponse toProductoResponse(Producto producto);
    LoteProduccionResponse toLoteProduccionResponse(LoteProduccion lote);
    BodegaResponse toBodegaResponse(Bodega bodega);
    UnidadMedidaResponse toUnidadMedidaResponse(UnidadMedida unidad);
}
