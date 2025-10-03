package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.MetodoValoracionRequest;
import com.example.minimal_prod_backend.dto.MetodoValoracionResponse;
import com.example.minimal_prod_backend.entity.MetodoValoracion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MetodoValoracionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    MetodoValoracion toEntity(MetodoValoracionRequest input);

    MetodoValoracionResponse toResponse(MetodoValoracion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(MetodoValoracionRequest input, @MappingTarget MetodoValoracion entity);
}