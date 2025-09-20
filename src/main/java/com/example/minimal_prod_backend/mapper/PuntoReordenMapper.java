package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.PuntoReordenInput;
import com.example.minimal_prod_backend.dto.PuntoReordenResponse;
import com.example.minimal_prod_backend.entity.PuntoReorden;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PuntoReordenMapper {

    PuntoReordenResponse toResponse(PuntoReorden entity);

    List<PuntoReordenResponse> toResponseList(List<PuntoReorden> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "unidad", ignore = true)
    PuntoReorden toEntity(PuntoReordenInput input);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "unidad", ignore = true)
    void updateEntityFromInput(PuntoReordenInput input, @MappingTarget PuntoReorden entity);
}
