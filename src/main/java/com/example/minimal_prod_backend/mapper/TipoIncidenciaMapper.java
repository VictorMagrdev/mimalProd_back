package com.example.minimal_prod_backend.mapper;


import com.example.minimal_prod_backend.dto.Request.TipoIncidenciaRequest;
import com.example.minimal_prod_backend.dto.Response.TipoIncidenciaResponse;
import com.example.minimal_prod_backend.entity.TipoIncidencia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoIncidenciaMapper {
    TipoIncidencia toEntity(TipoIncidenciaRequest dto);

    TipoIncidenciaResponse toResponse(TipoIncidencia entity);
}
