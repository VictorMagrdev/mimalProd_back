package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.EstadoAsignacionRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoAsignacionResponse;
import com.example.minimal_prod_backend.entity.EstadoAsignacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstadoAsignacionMapper {

    @Mapping(target = "id", ignore = true)
    EstadoAsignacion toEntity(EstadoAsignacionRequest input);

    EstadoAsignacionResponse toResponse(EstadoAsignacion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromInput(EstadoAsignacionRequest input, @MappingTarget EstadoAsignacion entity);
}
