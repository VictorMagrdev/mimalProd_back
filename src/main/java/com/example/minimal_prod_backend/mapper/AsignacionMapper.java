package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.AsignacionRequest;
import com.example.minimal_prod_backend.dto.AsignacionResponse;
import com.example.minimal_prod_backend.entity.Asignacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AsignacionMapper {

    @Mappings({
            @Mapping(target = "ordenTrabajo.id", source = "ordenTrabajoId"),
            @Mapping(target = "usuario.id", source = "usuarioId"),
            @Mapping(target = "asignadoPor.id", source = "asignadoPor"),
            @Mapping(target = "estadoAsignacion.id", source = "estadoAsignacionId"),
            @Mapping(target = "funcionTarea.id", source = "funcionTareaId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    Asignacion toEntity(AsignacionRequest input);

    @Mappings({
            @Mapping(target = "ordenTrabajoId", source = "ordenTrabajo.id"),
            @Mapping(target = "usuarioId", source = "usuario.id"),
            @Mapping(target = "asignadoPor", source = "asignadoPor.id"),
            @Mapping(target = "estadoAsignacionId", source = "estadoAsignacion.id"),
            @Mapping(target = "funcionTareaId", source = "funcionTarea.id")
    })
    AsignacionResponse toResponse(Asignacion asignacion);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "ordenTrabajo.id", source = "ordenTrabajoId"),
            @Mapping(target = "usuario.id", source = "usuarioId"),
            @Mapping(target = "asignadoPor.id", source = "asignadoPor"),
            @Mapping(target = "estadoAsignacion.id", source = "estadoAsignacionId"),
            @Mapping(target = "funcionTarea.id", source = "funcionTareaId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    void updateEntityFromInput(AsignacionRequest input, @MappingTarget Asignacion entity);
}
