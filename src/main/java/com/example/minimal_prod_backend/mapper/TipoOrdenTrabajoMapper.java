package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoOrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.TipoOrdenTrabajoResponse;
import com.example.minimal_prod_backend.entity.TipoOrdenTrabajo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoOrdenTrabajoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    TipoOrdenTrabajo toEntity(TipoOrdenTrabajoRequest input);

    TipoOrdenTrabajoResponse toResponse(TipoOrdenTrabajo entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(TipoOrdenTrabajoRequest input, @MappingTarget TipoOrdenTrabajo entity);
}