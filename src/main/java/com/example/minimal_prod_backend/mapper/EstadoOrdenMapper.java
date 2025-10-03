package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstadoOrdenRequest;
import com.example.minimal_prod_backend.dto.EstadoOrdenResponse;
import com.example.minimal_prod_backend.entity.EstadoOrden;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoOrdenMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    EstadoOrden toEntity(EstadoOrdenRequest input);

    List<EstadoOrdenResponse> toResponseList(List<EstadoOrden> conteos);

    EstadoOrdenResponse toResponse(EstadoOrden entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    void updateEntityFromInput(EstadoOrdenRequest input, @MappingTarget EstadoOrden entity);
}
