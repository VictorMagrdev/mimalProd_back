package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstadoAsignacionInput;
import com.example.minimal_prod_backend.dto.EstadoAsignacionResponse;
import com.example.minimal_prod_backend.entity.EstadoAsignacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EstadoAsignacionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    EstadoAsignacion toEntity(EstadoAsignacionInput input);

    EstadoAsignacionResponse toResponse(EstadoAsignacion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(EstadoAsignacionInput input, EstadoAsignacion entity);
}
