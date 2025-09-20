package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LoteProduccionMapper {

    @Mapping(source = "producto", target = "producto")
    LoteProduccionResponse toResponse(LoteProduccion entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    LoteProduccion toEntity(LoteProduccionInput dto);

    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    void updateEntityFromInput(LoteProduccionInput dto, @MappingTarget LoteProduccion entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "codigo", target = "codigo")
    @Mapping(source = "nombre", target = "nombre")
    ProductoResponse toProductoResponse(Producto producto);
}
