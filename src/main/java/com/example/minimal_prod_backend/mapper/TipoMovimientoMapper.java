package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoMovimientoInput;
import com.example.minimal_prod_backend.dto.TipoMovimientoResponse;
import com.example.minimal_prod_backend.entity.TipoMovimiento;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoMovimientoMapper {

    TipoMovimientoResponse toResponse(TipoMovimiento entity);

    TipoMovimiento toEntity(TipoMovimientoInput dto);

    List<TipoMovimientoResponse> toResponseList(List<TipoMovimiento> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(TipoMovimientoInput dto, @MappingTarget TipoMovimiento entity);
}
