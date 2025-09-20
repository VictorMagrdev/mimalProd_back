package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.TipoCostoInput;
import com.example.minimal_prod_backend.dto.TipoCostoResponse;
import com.example.minimal_prod_backend.entity.TipoCosto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoCostoMapper {

    TipoCostoResponse toResponse(TipoCosto entity);

    TipoCosto toEntity(TipoCostoInput dto);

    List<TipoCostoResponse> toResponseList(List<TipoCosto> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(TipoCostoInput dto, @MappingTarget TipoCosto entity);
}
