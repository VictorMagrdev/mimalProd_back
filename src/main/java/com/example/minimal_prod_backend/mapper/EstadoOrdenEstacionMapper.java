package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstadoOrdenEstacionInput;
import com.example.minimal_prod_backend.dto.EstadoOrdenEstacionResponse;
import com.example.minimal_prod_backend.entity.EstadoOrdenEstacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstadoOrdenEstacionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    EstadoOrdenEstacion toEntity(EstadoOrdenEstacionInput input);

    EstadoOrdenEstacionResponse toResponse(EstadoOrdenEstacion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(EstadoOrdenEstacionInput input, @MappingTarget EstadoOrdenEstacion entity);
}