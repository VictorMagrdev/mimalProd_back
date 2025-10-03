package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.PoliticaRequest;
import com.example.minimal_prod_backend.dto.PoliticaResponse;
import com.example.minimal_prod_backend.entity.Politica;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PoliticaMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "rolId", target = "rol.id"),
            @Mapping(source = "tagId", target = "tag.id"),
            @Mapping(source = "permisoId", target = "permiso.id")
    })
    Politica toEntity(PoliticaRequest input);

    @Mappings({
            @Mapping(source = "rol.id", target = "rolId"),
            @Mapping(source = "tag.id", target = "tagId"),
            @Mapping(source = "permiso.id", target = "permisoId")
    })
    PoliticaResponse toResponse(Politica entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "rolId", target = "rol.id"),
            @Mapping(source = "tagId", target = "tag.id"),
            @Mapping(source = "permisoId", target = "permiso.id")
    })
    void updateEntityFromInput(PoliticaRequest input, @MappingTarget Politica entity);
}
