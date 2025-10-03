package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.LoteProduccionRequest;
import com.example.minimal_prod_backend.dto.LoteProduccionResponse;
import com.example.minimal_prod_backend.entity.LoteProduccion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LoteProduccionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "productoId", target = "producto.id")
    })
    LoteProduccion toEntity(LoteProduccionRequest input);

    @Mappings({
            @Mapping(source = "producto.id", target = "productoId")
    })
    LoteProduccionResponse toResponse(LoteProduccion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "productoId", target = "producto.id")
    })
    void updateEntityFromInput(LoteProduccionRequest input, @MappingTarget LoteProduccion entity);
}