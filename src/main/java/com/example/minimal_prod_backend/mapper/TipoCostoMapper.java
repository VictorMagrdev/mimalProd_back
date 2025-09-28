package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoCostoInput;
import com.example.minimal_prod_backend.dto.TipoCostoResponse;
import com.example.minimal_prod_backend.entity.TipoCosto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TipoCostoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    TipoCosto toEntity(TipoCostoInput input);

    TipoCostoResponse toResponse(TipoCosto entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void updateEntityFromInput(TipoCostoInput input, @MappingTarget TipoCosto entity);
}