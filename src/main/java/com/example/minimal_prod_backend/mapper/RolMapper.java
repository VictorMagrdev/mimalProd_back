package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.RolInput;
import com.example.minimal_prod_backend.dto.RolResponse;
import com.example.minimal_prod_backend.entity.Rol;
import org.mapstruct.*;

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
