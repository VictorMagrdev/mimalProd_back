package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstadoOrdenTrabajoInput;
import com.example.minimal_prod_backend.dto.EstadoOrdenTrabajoResponse;
import com.example.minimal_prod_backend.entity.EstadoOrdenTrabajo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EstadoOrdenTrabajoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    EstadoOrdenTrabajo toEntity(EstadoOrdenTrabajoInput input);

    EstadoOrdenTrabajoResponse toResponse(EstadoOrdenTrabajo entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(EstadoOrdenTrabajoInput input, @MappingTarget EstadoOrdenTrabajo entity);
}