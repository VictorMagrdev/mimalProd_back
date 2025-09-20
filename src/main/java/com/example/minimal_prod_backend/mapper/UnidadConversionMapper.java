package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.UnidadConversionInput;
import com.example.minimal_prod_backend.dto.UnidadConversionResponse;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.entity.UnidadConversion;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadConversionMapper {

    UnidadConversionResponse toResponse(UnidadConversion entity);

    UnidadConversion toEntity(UnidadConversionInput dto);

    List<UnidadConversionResponse> toResponseList(List<UnidadConversion> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(UnidadConversionInput dto, @MappingTarget UnidadConversion entity);

    default UnidadMedidaResponse toUnidadMedidaResponse(UnidadMedida entity) {
        if (entity == null) return null;
        UnidadMedidaResponse dto = new UnidadMedidaResponse();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setAbreviatura(entity.getAbreviatura());
        dto.setEsActiva(entity.isEsActiva());
        dto.setEsBase(entity.isEsBase());
        dto.setCreadoEn(entity.getCreadoEn());
        return dto;
    }
}
