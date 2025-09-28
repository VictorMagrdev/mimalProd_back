package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.UnidadMedidaInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadMedidaMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "unidadMedidaTipoId", target = "tipo.id")
    })
    UnidadMedida toEntity(UnidadMedidaInput input);
    List<UnidadMedidaResponse> toResponseList(List<UnidadMedida> conteos);
    @Mappings({
        @Mapping(source = "tipo.id", target = "unidadMedidaTipoId")
    })
    UnidadMedidaResponse toResponse(UnidadMedida entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "unidadMedidaTipoId", target = "tipo.id")
    })
    void updateEntityFromInput(UnidadMedidaInput input, @MappingTarget UnidadMedida entity);
}