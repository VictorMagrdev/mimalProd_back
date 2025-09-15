package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.MetodoValoracionResponse;
import com.example.minimal_prod_backend.entity.MetodoValoracion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MetodoValoracionMapper {
    MetodoValoracionResponse toResponse(MetodoValoracion metodoValoracion);
}
