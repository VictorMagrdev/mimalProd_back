package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.MetodoValoracionInput;
import com.example.minimal_prod_backend.dto.MetodoValoracionResponse;
import com.example.minimal_prod_backend.entity.MetodoValoracion;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetodoValoracionMapper {

    MetodoValoracionResponse toResponse(MetodoValoracion entity);

    MetodoValoracion toEntity(MetodoValoracionInput dto);

    List<MetodoValoracionResponse> toResponseList(List<MetodoValoracion> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(MetodoValoracionInput dto, @MappingTarget MetodoValoracion entity);
}
