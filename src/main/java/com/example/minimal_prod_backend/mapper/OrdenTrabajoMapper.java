package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.OrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.OrdenTrabajoResponse;
import com.example.minimal_prod_backend.entity.OrdenTrabajo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrdenTrabajoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "tipoId", target = "tipo.id"),
            @Mapping(source = "estadoId", target = "estado.id")
    })
    OrdenTrabajo toEntity(OrdenTrabajoRequest input);

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
    void updateEntityFromInput(OrdenTrabajoRequest input, @MappingTarget OrdenTrabajo entity);
}