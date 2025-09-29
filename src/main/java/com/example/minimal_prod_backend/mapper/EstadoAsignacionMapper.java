package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstadoAsignacionRequest;
import com.example.minimal_prod_backend.dto.EstadoAsignacionResponse;
import com.example.minimal_prod_backend.entity.EstadoAsignacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstadoAsignacionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    EstadoAsignacion toEntity(EstadoAsignacionRequest input);

    EstadoAsignacionResponse toResponse(EstadoAsignacion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(EstadoAsignacionRequest input, EstadoAsignacion entity);
}
