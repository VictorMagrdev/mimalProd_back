package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.DepreciacionRequest;
import com.example.minimal_prod_backend.dto.DepreciacionResponse;
import com.example.minimal_prod_backend.entity.Depreciacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DepreciacionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "maquina.id", source = "maquinaId")
    Depreciacion toEntity(DepreciacionRequest request);

    @Mapping(target = "maquinaId", source = "maquina.id")
    DepreciacionResponse toResponse(Depreciacion entity);
}
