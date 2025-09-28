package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.CostoOrdenInput;
import com.example.minimal_prod_backend.dto.CostoOrdenResponse;
import com.example.minimal_prod_backend.entity.CostoOrden;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CostoOrdenMapper {

    @Mappings({
            @Mapping(target = "orden.id", source = "ordenId"),
            @Mapping(target = "tipoCosto.id", source = "tipoCostoId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "registradoEn", ignore = true)
    })
    CostoOrden toEntity(CostoOrdenInput input);

    @Mappings({
            @Mapping(target = "ordenId", source = "orden.id"),
            @Mapping(target = "tipoCostoId", source = "tipoCosto.id"),
            @Mapping(target = "registradoEn", source = "registradoEn")
    })
    CostoOrdenResponse toResponse(CostoOrden costoOrden);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "orden.id", source = "ordenId"),
            @Mapping(target = "tipoCosto.id", source = "tipoCostoId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "registradoEn", ignore = true)
    })
    void updateEntityFromInput(CostoOrdenInput input, CostoOrden entity);
}
