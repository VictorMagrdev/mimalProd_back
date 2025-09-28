package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstadoAprobacionInput;
import com.example.minimal_prod_backend.dto.EstadoAprobacionResponse;
import com.example.minimal_prod_backend.entity.EstadoAprobacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EstadoAprobacionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    EstadoAprobacion toEntity(EstadoAprobacionInput input);

    EstadoAprobacionResponse toResponse(EstadoAprobacion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(EstadoAprobacionInput input, EstadoAprobacion entity);
}
