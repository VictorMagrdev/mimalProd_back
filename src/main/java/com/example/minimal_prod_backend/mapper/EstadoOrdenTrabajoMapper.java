package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.EstadoOrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoOrdenTrabajoResponse;
import com.example.minimal_prod_backend.entity.EstadoOrdenTrabajo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstadoOrdenTrabajoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    EstadoOrdenTrabajo toEntity(EstadoOrdenTrabajoRequest input);

    EstadoOrdenTrabajoResponse toResponse(EstadoOrdenTrabajo entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(EstadoOrdenTrabajoRequest input, @MappingTarget EstadoOrdenTrabajo entity);
}