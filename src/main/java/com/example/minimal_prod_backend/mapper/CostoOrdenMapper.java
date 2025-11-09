package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.CostoOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.CostoOrdenResponse;
import com.example.minimal_prod_backend.entity.CostoOrden;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CostoOrdenMapper {

    @Mappings({
            @Mapping(target = "orden.id", source = "ordenId"),
            @Mapping(target = "tipoCosto.id", source = "tipoCostoId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "registradoEn", ignore = true)
    })
    CostoOrden toEntity(CostoOrdenRequest input);

    List<CostoOrdenResponse> toResponseList(List<CostoOrden> conteos);

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
    void updateEntityFromInput(CostoOrdenRequest input, @MappingTarget CostoOrden entity);
}
