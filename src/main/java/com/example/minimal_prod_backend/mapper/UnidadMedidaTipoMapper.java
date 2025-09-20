package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.UnidadMedidaTipoInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaTipoResponse;
import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadMedidaTipoMapper {

    UnidadMedidaTipoResponse toResponse(UnidadMedidaTipo entity);

    UnidadMedidaTipo toEntity(UnidadMedidaTipoInput dto);

    List<UnidadMedidaTipoResponse> toResponseList(List<UnidadMedidaTipo> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(UnidadMedidaTipoInput dto, @MappingTarget UnidadMedidaTipo entity);
}
