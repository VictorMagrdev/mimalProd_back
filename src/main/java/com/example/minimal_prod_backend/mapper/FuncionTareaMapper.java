package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.FuncionTareaInput;
import com.example.minimal_prod_backend.dto.FuncionTareaResponse;
import com.example.minimal_prod_backend.entity.FuncionTarea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FuncionTareaMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    FuncionTarea toEntity(FuncionTareaInput input);

    FuncionTareaResponse toResponse(FuncionTarea entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(FuncionTareaInput input, @MappingTarget FuncionTarea entity);
}