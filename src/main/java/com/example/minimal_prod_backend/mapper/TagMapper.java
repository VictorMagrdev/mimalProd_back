package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TagInput;
import com.example.minimal_prod_backend.dto.TagResponse;
import com.example.minimal_prod_backend.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ownerRoleId", target = "ownerRole.id")
    })
    Tag toEntity(TagInput input);

    @Mappings({
        @Mapping(source = "ownerRole.id", target = "ownerRoleId")
    })
    TagResponse toResponse(Tag entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ownerRoleId", target = "ownerRole.id")
    })
    void updateEntityFromInput(TagInput input, @MappingTarget Tag entity);
}
