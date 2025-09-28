package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.HojaTimesheetInput;
import com.example.minimal_prod_backend.dto.HojaTimesheetResponse;
import com.example.minimal_prod_backend.entity.HojaTimesheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface HojaTimesheetMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "usuarioId", target = "usuario.id"),
            @Mapping(source = "estadoAprobacionId", target = "estadoAprobacion.id"),
            @Mapping(source = "aprobadoPor", target = "aprobadoPor.id")
    })
    HojaTimesheet toEntity(HojaTimesheetInput input);

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
    void updateEntityFromInput(HojaTimesheetInput input, @MappingTarget HojaTimesheet entity);
}