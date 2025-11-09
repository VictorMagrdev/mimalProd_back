package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.EstadoOrdenEstacionRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoOrdenEstacionResponse;
import com.example.minimal_prod_backend.entity.EstadoOrdenEstacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstadoOrdenEstacionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    EstadoOrdenEstacion toEntity(EstadoOrdenEstacionRequest input);

    EstadoOrdenEstacionResponse toResponse(EstadoOrdenEstacion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(EstadoOrdenEstacionRequest input, @MappingTarget EstadoOrdenEstacion entity);
}