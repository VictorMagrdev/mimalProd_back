package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.RegistroTiempoRequest;
import com.example.minimal_prod_backend.dto.RegistroTiempoResponse;
import com.example.minimal_prod_backend.entity.RegistroTiempo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroTiempoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "asignacion", ignore = true)
    @Mapping(target = "tipoActividad", ignore = true)
    @Mapping(target = "tipoCosto", ignore = true)
    @Mapping(target = "estadoAprobacion", ignore = true)
    @Mapping(target = "hojaTimesheet", ignore = true)
    @Mapping(target = "duracion", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "actualizadoEn", ignore = true)
    RegistroTiempo toEntity(RegistroTiempoRequest request);

    @Mapping(source = "asignacion.id", target = "asignacionId")
    @Mapping(source = "tipoActividad.id", target = "tipoActividadId")
    @Mapping(source = "tipoCosto.id", target = "tipoCostoId")
    @Mapping(source = "estadoAprobacion.id", target = "estadoAprobacionId")
    @Mapping(source = "hojaTimesheet.id", target = "hojaTimesheetId")
    RegistroTiempoResponse toResponse(RegistroTiempo entity);
}
