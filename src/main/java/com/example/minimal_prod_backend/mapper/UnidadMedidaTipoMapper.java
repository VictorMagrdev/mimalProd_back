package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.UnidadMedidaTipoRequest;
import com.example.minimal_prod_backend.dto.UnidadMedidaTipoResponse;
import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadMedidaTipoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    UnidadMedidaTipo toEntity(UnidadMedidaTipoRequest input);

    List<UnidadMedidaTipoResponse> toResponseList(List<UnidadMedidaTipo> conteos);

    UnidadMedidaTipoResponse toResponse(UnidadMedidaTipo entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void updateEntityFromInput(UnidadMedidaTipoRequest input, @MappingTarget UnidadMedidaTipo entity);
}
