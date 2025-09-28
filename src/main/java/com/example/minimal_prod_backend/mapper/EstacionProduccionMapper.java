package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.EstacionProduccionInput;
import com.example.minimal_prod_backend.dto.EstacionProduccionResponse;
import com.example.minimal_prod_backend.entity.EstacionProduccion;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstacionProduccionMapper {

    EstacionProduccionResponse toResponse(EstacionProduccion entity);

    EstacionProduccion toEntity(EstacionProduccionInput dto);

    List<EstacionProduccionResponse> toResponseList(List<EstacionProduccion> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(EstacionProduccionInput dto, @MappingTarget EstacionProduccion entity);
}
