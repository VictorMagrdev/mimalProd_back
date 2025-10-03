package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioRequest;
import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioResponse;
import com.example.minimal_prod_backend.entity.DiscrepanciaInventario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscrepanciaInventarioMapper {

    @Mappings({
            @Mapping(target = "conteo.id", source = "conteoId"),
            @Mapping(target = "id", ignore = true)
    })
    DiscrepanciaInventario toEntity(DiscrepanciaInventarioRequest input);

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
    void updateEntityFromInput(DiscrepanciaInventarioRequest input, @MappingTarget DiscrepanciaInventario entity);
}
