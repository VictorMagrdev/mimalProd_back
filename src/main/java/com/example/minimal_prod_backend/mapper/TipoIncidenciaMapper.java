package com.example.minimal_prod_backend.mapper;


import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.TipoIncidencia;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoIncidenciaMapper {
    TipoIncidencia toEntity(TipoIncidenciaRequest dto);
    TipoIncidenciaResponse toResponse(TipoIncidencia entity);
}
