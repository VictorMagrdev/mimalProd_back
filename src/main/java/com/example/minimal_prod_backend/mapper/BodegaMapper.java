package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.BodegaInput;
import com.example.minimal_prod_backend.dto.BodegaResponse;
import com.example.minimal_prod_backend.entity.Bodega;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BodegaMapper {

    @Mappings({
            @Mapping(target = "tipo.id", source = "tipoBodegaId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    Bodega toEntity(BodegaInput input);

    @Mappings({
            @Mapping(target = "tipoBodegaId", source = "tipo.id")
    })
    BodegaResponse toResponse(Bodega bodega);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "tipo.id", source = "tipoBodegaId"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    void updateEntityFromInput(BodegaInput input, Bodega entity);
}
