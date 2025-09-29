package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoActividadInput;
import com.example.minimal_prod_backend.dto.TipoActividadResponse;
import com.example.minimal_prod_backend.entity.TipoActividad;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoActividadMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    TipoActividad toEntity(TipoActividadInput input);

    TipoActividadResponse toResponse(TipoActividad entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(TipoActividadInput input, @MappingTarget TipoActividad entity);
}