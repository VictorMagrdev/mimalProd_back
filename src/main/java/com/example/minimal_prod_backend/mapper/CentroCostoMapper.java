package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.CentroCostoInput;
import com.example.minimal_prod_backend.dto.CentroCostoResponse;
import com.example.minimal_prod_backend.entity.CentroCosto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CentroCostoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    CentroCosto toEntity(CentroCostoInput input);

    CentroCostoResponse toResponse(CentroCosto entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(CentroCostoInput input, @MappingTarget CentroCosto entity);
}
