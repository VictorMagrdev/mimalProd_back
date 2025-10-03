package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.PermisoRequest;
import com.example.minimal_prod_backend.dto.PermisoResponse;
import com.example.minimal_prod_backend.entity.Permiso;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PermisoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Permiso toEntity(PermisoRequest input);

    PermisoResponse toResponse(Permiso entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(PermisoRequest input, @MappingTarget Permiso entity);
}
