package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.ConteoCiclicoResponse;
import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioInput;
import com.example.minimal_prod_backend.dto.DiscrepanciaInventarioResponse;
import com.example.minimal_prod_backend.entity.DiscrepanciaInventario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscrepanciaInventarioMapper {

    @Mapping(target = "conteo", expression = "java(entity.getConteo() != null ? toConteoResponse(entity.getConteo().getId()) : null)")
    DiscrepanciaInventarioResponse toResponse(DiscrepanciaInventario entity);

    List<DiscrepanciaInventarioResponse> toResponseList(List<DiscrepanciaInventario> entities);

    @Mapping(target = "conteo", ignore = true) // se carga manualmente en el service
    DiscrepanciaInventario toEntity(DiscrepanciaInventarioInput dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "conteo", ignore = true)
    void updateEntityFromInput(DiscrepanciaInventarioInput dto, @MappingTarget DiscrepanciaInventario entity);

    // helper para mapear solo el id de conteo
    default ConteoCiclicoResponse toConteoResponse(Long id) {
        ConteoCiclicoResponse response = new ConteoCiclicoResponse();
        response.setId(id);
        return response;
    }
}
