package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.MetodoValoracionInput;
import com.example.minimal_prod_backend.dto.MetodoValoracionResponse;
import com.example.minimal_prod_backend.entity.MetodoValoracion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MetodoValoracionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    MetodoValoracion toEntity(MetodoValoracionInput input);

    MetodoValoracionResponse toResponse(MetodoValoracion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(MetodoValoracionInput input, @MappingTarget MetodoValoracion entity);
}