package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.BodegaRequest;
import com.example.minimal_prod_backend.dto.Response.BodegaResponse;
import com.example.minimal_prod_backend.entity.Bodega;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BodegaMapper {

    @Mappings({
            @Mapping(target = "tipo", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    Bodega toEntity(BodegaRequest input);

    @Mappings({
            @Mapping(source = "tipo.id", target = "tipoBodegaId")
    })
    BodegaResponse toResponse(Bodega bodega);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "tipo", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    void updateEntityFromInput(BodegaRequest input, @MappingTarget Bodega entity);
}
