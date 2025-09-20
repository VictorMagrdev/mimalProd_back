package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.CostoOrdenInput;
import com.example.minimal_prod_backend.dto.CostoOrdenResponse;
import com.example.minimal_prod_backend.dto.OrdenProduccionResponse;
import com.example.minimal_prod_backend.dto.TipoCostoResponse;
import com.example.minimal_prod_backend.entity.CostoOrden;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.entity.TipoCosto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CostoOrdenMapper {

    CostoOrdenResponse toResponse(CostoOrden entity);

    CostoOrden toEntity(CostoOrdenInput dto);

    List<CostoOrdenResponse> toResponseList(List<CostoOrden> entities);

    OrdenProduccionResponse toOrdenResponse(OrdenProduccion orden);

    TipoCostoResponse toTipoCostoResponse(TipoCosto tipoCosto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(CostoOrdenInput dto, @MappingTarget CostoOrden entity);
}
