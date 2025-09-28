package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.UnidadMedidaInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.dto.UnidadMedidaTipoResponse;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE) // ← Agregar esta línea
public interface UnidadMedidaMapper {

    // Método principal para mapeo simple
    @Named("toResponseSimple")
    UnidadMedidaResponse toResponse(UnidadMedida entity);

    // Método para mapeo con tipo - hacerlo único
    @Named("toResponseWithTipo")
    @Mapping(target = "tipo", source = "tipo")
    UnidadMedidaResponse toResponseWithTipo(UnidadMedida entity);

    // Especificar cuál método usar para la lista
    @IterableMapping(qualifiedByName = "toResponseSimple")
    List<UnidadMedidaResponse> toResponseList(List<UnidadMedida> entities);

    UnidadMedida toEntity(UnidadMedidaInput dto);

    UnidadMedidaTipoResponse toTipoResponse(UnidadMedidaTipo tipo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(UnidadMedidaInput dto, @MappingTarget UnidadMedida entity);
}