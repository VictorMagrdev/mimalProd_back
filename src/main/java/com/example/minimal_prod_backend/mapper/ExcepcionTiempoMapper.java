package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.ExcepcionTiempoRequest;
import com.example.minimal_prod_backend.dto.Response.ExcepcionTiempoResponse;
import com.example.minimal_prod_backend.entity.ExcepcionTiempo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExcepcionTiempoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "resuelto", ignore = true)
    ExcepcionTiempo toEntity(ExcepcionTiempoRequest request);

    @Mapping(source = "usuario.id", target = "usuarioId")
    ExcepcionTiempoResponse toResponse(ExcepcionTiempo entity);
}
