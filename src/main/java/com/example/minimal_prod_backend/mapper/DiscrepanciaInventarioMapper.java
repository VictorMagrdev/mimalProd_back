package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioInput;
import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioResponse;
import com.example.minimal_prod_backend.entity.DiscrepanciaInventario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscrepanciaInventarioMapper {

    @Mappings({
            @Mapping(target = "conteo.id", source = "conteoId"),
            @Mapping(target = "id", ignore = true)
    })
    DiscrepanciaInventario toEntity(DiscrepanciaInventarioInput input);
    List<DiscrepanciaInventarioResponse> toResponseList(List<DiscrepanciaInventario> conteos);

    @Mappings({
            @Mapping(target = "conteoId", source = "conteo.id")
    })
    DiscrepanciaInventarioResponse toResponse(DiscrepanciaInventario entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "conteo.id", source = "conteoId"),
            @Mapping(target = "id", ignore = true)
    })
    void updateEntityFromInput(DiscrepanciaInventarioInput input, DiscrepanciaInventario entity);
}
