package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.EstadoAprobacionRequest;
import com.example.minimal_prod_backend.dto.Response.EstadoAprobacionResponse;
import com.example.minimal_prod_backend.entity.EstadoAprobacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstadoAprobacionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    EstadoAprobacion toEntity(EstadoAprobacionRequest input);

    EstadoAprobacionResponse toResponse(EstadoAprobacion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(EstadoAprobacionRequest input, @MappingTarget EstadoAprobacion entity);
}
