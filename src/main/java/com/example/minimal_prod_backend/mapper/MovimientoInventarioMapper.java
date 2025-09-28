package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoInventarioMapper {

    MovimientoInventarioResponse toResponse(MovimientoInventario entity);

    List<MovimientoInventarioResponse> toResponseList(List<MovimientoInventario> entities);

    MovimientoInventario toEntity(MovimientoInventarioInput dto);

    BodegaResponse toBodegaResponse(Bodega bodega);

    TipoMovimientoResponse toTipoMovimientoResponse(TipoMovimiento tipoMovimiento);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(MovimientoInventarioInput dto, @MappingTarget MovimientoInventario entity);
}
