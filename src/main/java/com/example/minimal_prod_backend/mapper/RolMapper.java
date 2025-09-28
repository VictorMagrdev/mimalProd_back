package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.RolInput;
import com.example.minimal_prod_backend.dto.RolResponse;
import com.example.minimal_prod_backend.entity.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Rol toEntity(RolInput input);

    RolResponse toResponse(Rol entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(RolInput input, @MappingTarget Rol entity);
}
