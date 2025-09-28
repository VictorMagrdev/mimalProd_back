package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LineaOrdenMapper {

    LineaOrdenResponse toResponse(LineaOrden entity);

    List<LineaOrdenResponse> toResponseList(List<LineaOrden> entities);

    LineaOrden toEntity(LineaOrdenInput dto);

    OrdenProduccionResponse toOrdenResponse(OrdenProduccion orden);

    ProductoResponse toProductoResponse(Producto producto);

    UnidadMedidaResponse toUnidadResponse(UnidadMedida unidad);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(LineaOrdenInput dto, @MappingTarget LineaOrden entity);
}
