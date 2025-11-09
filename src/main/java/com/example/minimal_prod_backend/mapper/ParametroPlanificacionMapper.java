package com.example.minimal_prod_backend.mapper;


import com.example.minimal_prod_backend.dto.Request.ParametroPlanificacionRequest;
import com.example.minimal_prod_backend.dto.Response.ParametroPlanificacionResponse;
import com.example.minimal_prod_backend.entity.ParametroPlanificacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ParametroPlanificacionMapper {

    @Mappings({
            @Mapping(target = "producto.id", source = "productoId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "actualizadoEn", ignore = true)
    })
    ParametroPlanificacion toEntity(ParametroPlanificacionRequest request);

    @Mappings({
            @Mapping(target = "productoId", source = "producto.id"),
            @Mapping(target = "productoCodigo", source = "producto.codigo"),
            @Mapping(target = "productoNombre", source = "producto.nombre")
    })
    ParametroPlanificacionResponse toResponse(ParametroPlanificacion parametro);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "producto.id", source = "productoId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "actualizadoEn", ignore = true)
    })
    void updateEntityFromRequest(ParametroPlanificacionRequest request, @MappingTarget ParametroPlanificacion entity);
}