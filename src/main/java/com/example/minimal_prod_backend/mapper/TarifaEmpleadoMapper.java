package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.TarifaEmpleadoRequest;
import com.example.minimal_prod_backend.dto.Response.TarifaEmpleadoResponse;
import com.example.minimal_prod_backend.entity.TarifaEmpleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarifaEmpleadoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    TarifaEmpleado toEntity(TarifaEmpleadoRequest request);

    @Mapping(source = "usuario.id", target = "usuarioId")
    TarifaEmpleadoResponse toResponse(TarifaEmpleado entity);
}
