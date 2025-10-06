package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TagRequest;
import com.example.minimal_prod_backend.dto.TagResponse;
import com.example.minimal_prod_backend.entity.Tag;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Tag toEntity(TagRequest input);

    @Mappings({
    })
    TagResponse toResponse(Tag entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void updateEntityFromInput(TagRequest input, @MappingTarget Tag entity);
}
