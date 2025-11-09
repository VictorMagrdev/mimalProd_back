package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.TipoBodegaRequest;
import com.example.minimal_prod_backend.dto.Response.TipoBodegaResponse;
import com.example.minimal_prod_backend.entity.TipoBodega;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoBodegaMapper {

    TipoBodega toEntity(TipoBodegaRequest request);

    TipoBodegaResponse toResponse(TipoBodega entity);

    List<TipoBodegaResponse> toResponseList(List<TipoBodega> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(TipoBodegaRequest request, @MappingTarget TipoBodega entity);
}

