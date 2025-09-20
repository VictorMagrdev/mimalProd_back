package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.UnidadMedidaInput;
import com.example.minimal_prod_backend.dto.UnidadMedidaResponse;
import com.example.minimal_prod_backend.dto.UnidadMedidaTipoResponse;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.entity.UnidadMedidaTipo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadMedidaMapper {

    UnidadMedidaResponse toResponse(UnidadMedida entity);

    UnidadMedida toEntity(UnidadMedidaInput dto);

    List<UnidadMedidaResponse> toResponseList(List<UnidadMedida> entities);

    // 🔹 Mapping de nested Tipo
    @Mapping(target = "tipo", source = "tipo")
    UnidadMedidaResponse toResponseWithTipo(UnidadMedida entity);

    UnidadMedidaTipoResponse toTipoResponse(UnidadMedidaTipo tipo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(UnidadMedidaInput dto, @MappingTarget UnidadMedida entity);
}
