package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.DepreciacionRequest;
import com.example.minimal_prod_backend.dto.Response.DepreciacionResponse;
import com.example.minimal_prod_backend.entity.Depreciacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DepreciacionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "maquina.id", source = "maquinaId")
    @Mapping(target = "calculadoEn", ignore = true)
    Depreciacion toEntity(DepreciacionRequest request);

    @Mapping(target = "maquinaId", source = "maquina.id")
    DepreciacionResponse toResponse(Depreciacion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "maquina", ignore = true)
    @Mapping(target = "calculadoEn", ignore = true)
    void updateEntityFromInput(DepreciacionRequest request, @MappingTarget Depreciacion entity);
}
