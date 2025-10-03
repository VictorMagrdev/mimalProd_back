package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstacionProduccionRequest;
import com.example.minimal_prod_backend.dto.EstacionProduccionResponse;
import com.example.minimal_prod_backend.entity.EstacionProduccion;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstacionProduccionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "creadoEn", ignore = true)
    })
    EstacionProduccion toEntity(EstacionProduccionRequest input);

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
    void updateEntityFromInput(EstacionProduccionRequest input, @MappingTarget EstacionProduccion entity);
}
