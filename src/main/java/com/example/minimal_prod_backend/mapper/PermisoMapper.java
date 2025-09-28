package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.PermisoInput;
import com.example.minimal_prod_backend.dto.PermisoResponse;
import com.example.minimal_prod_backend.entity.Permiso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PermisoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Permiso toEntity(PermisoInput input);

    PermisoResponse toResponse(Permiso entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(PermisoInput input, @MappingTarget Permiso entity);
}
