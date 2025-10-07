package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.LoteProduccionRequest;
import com.example.minimal_prod_backend.dto.LoteProduccionResponse;
import com.example.minimal_prod_backend.entity.LoteProduccion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LoteProduccionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "producto", ignore = true)
    })
    LoteProduccion toEntity(LoteProduccionRequest input);

    @Mappings({
            @Mapping(source = "producto.id", target = "productoId")
    })
    LoteProduccionResponse toResponse(LoteProduccion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "producto", ignore = true)
    })
    void updateEntityFromInput(LoteProduccionRequest input, @MappingTarget LoteProduccion entity);
}
