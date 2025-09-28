package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OrdenTrabajoInput;
import com.example.minimal_prod_backend.dto.OrdenTrabajoResponse;
import com.example.minimal_prod_backend.entity.OrdenTrabajo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OrdenTrabajoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "tipoId", target = "tipo.id"),
            @Mapping(source = "estadoId", target = "estado.id")
    })
    OrdenTrabajo toEntity(OrdenTrabajoInput input);

    @Mappings({
        @Mapping(source = "tipo.id", target = "tipoId"),
        @Mapping(source = "estado.id", target = "estadoId")
    })
    OrdenTrabajoResponse toResponse(OrdenTrabajo entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "tipoId", target = "tipo.id"),
            @Mapping(source = "estadoId", target = "estado.id")
    })
    void updateEntityFromInput(OrdenTrabajoInput input, @MappingTarget OrdenTrabajo entity);
}