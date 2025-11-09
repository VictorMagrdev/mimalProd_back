package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.HojaTimesheetRequest;
import com.example.minimal_prod_backend.dto.Response.HojaTimesheetResponse;
import com.example.minimal_prod_backend.entity.HojaTimesheet;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HojaTimesheetMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "usuarioId", target = "usuario.id"),
            @Mapping(source = "estadoAprobacionId", target = "estadoAprobacion.id"),
            @Mapping(source = "aprobadoPor", target = "aprobadoPor.id")
    })
    HojaTimesheet toEntity(HojaTimesheetRequest input);

    @Mappings({
            @Mapping(source = "usuario.id", target = "usuarioId"),
            @Mapping(source = "estadoAprobacion.id", target = "estadoAprobacionId"),
            @Mapping(source = "aprobadoPor.id", target = "aprobadoPor")
    })
    HojaTimesheetResponse toResponse(HojaTimesheet entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "usuarioId", target = "usuario.id"),
            @Mapping(source = "estadoAprobacionId", target = "estadoAprobacion.id"),
            @Mapping(source = "aprobadoPor", target = "aprobadoPor.id")
    })
    void updateEntityFromInput(HojaTimesheetRequest input, @MappingTarget HojaTimesheet entity);
}