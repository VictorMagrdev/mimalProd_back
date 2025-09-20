package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConteoCiclicoMapper {

    ConteoCiclicoResponse toResponse(ConteoCiclico entity);

    List<ConteoCiclicoResponse> toResponseList(List<ConteoCiclico> entities);

    ProductoResponse toProductoResponse(Producto entity);
    BodegaResponse toBodegaResponse(Bodega entity);
    LoteProduccionResponse toLoteProduccionResponse(LoteProduccion entity);
    UnidadMedidaResponse toUnidadMedidaResponse(UnidadMedida entity);

    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "bodega", ignore = true)
    @Mapping(target = "lote", ignore = true)
    @Mapping(target = "unidad", ignore = true)
    ConteoCiclico toEntity(ConteoCiclicoInput dto);

    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "bodega", ignore = true)
    @Mapping(target = "lote", ignore = true)
    @Mapping(target = "unidad", ignore = true)
    void updateEntityFromInput(ConteoCiclicoInput dto, @MappingTarget ConteoCiclico entity);
}
