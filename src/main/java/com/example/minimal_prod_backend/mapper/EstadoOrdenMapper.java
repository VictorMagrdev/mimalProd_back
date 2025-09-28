package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstadoOrdenInput;
import com.example.minimal_prod_backend.dto.EstadoOrdenResponse;
import com.example.minimal_prod_backend.entity.EstadoOrden;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoOrdenMapper {

    EstadoOrdenResponse toResponse(EstadoOrden entity);

    EstadoOrden toEntity(EstadoOrdenInput dto);

    List<EstadoOrdenResponse> toResponseList(List<EstadoOrden> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(EstadoOrdenInput dto, @MappingTarget EstadoOrden entity);
}
