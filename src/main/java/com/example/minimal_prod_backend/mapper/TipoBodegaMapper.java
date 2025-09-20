package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoBodegaInput;
import com.example.minimal_prod_backend.dto.TipoBodegaResponse;
import com.example.minimal_prod_backend.entity.TipoBodega;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoBodegaMapper {

    TipoBodegaResponse toResponse(TipoBodega entity);

    TipoBodega toEntity(TipoBodegaInput dto);

    List<TipoBodegaResponse> toResponseList(List<TipoBodega> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(TipoBodegaInput dto, @MappingTarget TipoBodega entity);
}
