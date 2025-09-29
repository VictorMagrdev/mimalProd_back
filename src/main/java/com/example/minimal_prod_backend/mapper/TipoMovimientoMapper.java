package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoMovimientoRequest;
import com.example.minimal_prod_backend.dto.TipoMovimientoResponse;
import com.example.minimal_prod_backend.entity.TipoMovimiento;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoMovimientoMapper {

    TipoMovimiento toEntity(TipoMovimientoRequest request);

    TipoMovimientoResponse toResponse(TipoMovimiento entity);

    List<TipoMovimientoResponse> toResponseList(List<TipoMovimiento> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(TipoMovimientoRequest request, @MappingTarget TipoMovimiento entity);
}
