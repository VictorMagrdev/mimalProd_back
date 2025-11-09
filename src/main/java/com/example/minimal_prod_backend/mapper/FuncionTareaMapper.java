package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.FuncionTareaRequest;
import com.example.minimal_prod_backend.dto.Response.FuncionTareaResponse;
import com.example.minimal_prod_backend.entity.FuncionTarea;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FuncionTareaMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    FuncionTarea toEntity(FuncionTareaRequest input);

    FuncionTareaResponse toResponse(FuncionTarea entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(FuncionTareaRequest input, @MappingTarget FuncionTarea entity);
}