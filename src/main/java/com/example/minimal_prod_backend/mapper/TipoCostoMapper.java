package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.TipoCostoRequest;
import com.example.minimal_prod_backend.dto.Response.TipoCostoResponse;
import com.example.minimal_prod_backend.entity.TipoCosto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoCostoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    TipoCosto toEntity(TipoCostoRequest input);

    List<TipoCostoResponse> toResponseList(List<TipoCosto> conteos);

    TipoCostoResponse toResponse(TipoCosto entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void updateEntityFromInput(TipoCostoRequest input, @MappingTarget TipoCosto entity);
}