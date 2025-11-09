package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.TipoMovimientoRequest;
import com.example.minimal_prod_backend.dto.Response.TipoMovimientoResponse;
import com.example.minimal_prod_backend.entity.TipoMovimiento;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoMovimientoMapper {

    TipoMovimiento toEntity(TipoMovimientoRequest request);

    TipoMovimientoResponse toResponse(TipoMovimiento entity);

    List<TipoMovimientoResponse> toResponseList(List<TipoMovimiento> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(TipoMovimientoRequest request, @MappingTarget TipoMovimiento entity);
}
