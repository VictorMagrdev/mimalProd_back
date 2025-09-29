package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.LoteProduccionInput;
import com.example.minimal_prod_backend.dto.LoteProduccionResponse;
import com.example.minimal_prod_backend.entity.LoteProduccion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LoteProduccionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "productoId", target = "producto.id")
    })
    LoteProduccion toEntity(LoteProduccionInput input);

    @Mappings({
            @Mapping(source = "producto.id", target = "productoId")
    })
    LoteProduccionResponse toResponse(LoteProduccion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "productoId", target = "producto.id")
    })
    void updateEntityFromInput(LoteProduccionInput input, @MappingTarget LoteProduccion entity);
}