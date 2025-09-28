package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstacionProduccionInput;
import com.example.minimal_prod_backend.dto.EstacionProduccionResponse;
import com.example.minimal_prod_backend.entity.EstacionProduccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstacionProduccionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    EstacionProduccion toEntity(EstacionProduccionInput input);
    List<EstacionProduccionResponse> toResponseList(List<EstacionProduccion> conteos);
    @Mappings({
            @Mapping(target = "creadoEn", source = "creadoEn")
    })
    EstacionProduccionResponse toResponse(EstacionProduccion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    void updateEntityFromInput(EstacionProduccionInput input, EstacionProduccion entity);
}
