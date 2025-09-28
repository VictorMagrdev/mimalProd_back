package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoOrdenTrabajoInput;
import com.example.minimal_prod_backend.dto.TipoOrdenTrabajoResponse;
import com.example.minimal_prod_backend.entity.TipoOrdenTrabajo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TipoOrdenTrabajoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    TipoOrdenTrabajo toEntity(TipoOrdenTrabajoInput input);

    TipoOrdenTrabajoResponse toResponse(TipoOrdenTrabajo entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(TipoOrdenTrabajoInput input, @MappingTarget TipoOrdenTrabajo entity);
}