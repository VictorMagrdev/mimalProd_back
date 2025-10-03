package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.UnidadMedidaRequest;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadMedidaMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "unidadMedidaTipoId", target = "tipo.id")
    })
    UnidadMedida toEntity(UnidadMedidaRequest input);

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
    void updateEntityFromInput(UnidadMedidaRequest input, @MappingTarget UnidadMedida entity);
}