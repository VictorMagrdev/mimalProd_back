package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.UnidadMedidaTipoInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaTipoResponse;
import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadMedidaTipoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    UnidadMedidaTipo toEntity(UnidadMedidaTipoInput input);
    List<UnidadMedidaTipoResponse> toResponseList(List<UnidadMedidaTipo> conteos);
    UnidadMedidaTipoResponse toResponse(UnidadMedidaTipo entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void updateEntityFromInput(UnidadMedidaTipoInput input, UnidadMedidaTipo entity);
}
