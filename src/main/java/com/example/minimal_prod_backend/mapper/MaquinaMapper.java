package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.MaquinaRequest;
import com.example.minimal_prod_backend.dto.MaquinaResponse;
import com.example.minimal_prod_backend.entity.Maquina;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MaquinaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "centroCosto.id", source = "centroCostoId")
    Maquina toEntity(MaquinaRequest request);

    @Mapping(target = "centroCostoId", source = "centroCosto.id")
    MaquinaResponse toResponse(Maquina entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "centroCosto.id", source = "centroCostoId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(MaquinaRequest request, @MappingTarget Maquina entity);
}
