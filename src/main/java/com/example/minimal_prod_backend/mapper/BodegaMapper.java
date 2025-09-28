package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.BodegaInput;
import com.example.minimal_prod_backend.dto.BodegaResponse;
import com.example.minimal_prod_backend.dto.TipoBodegaResponse;
import com.example.minimal_prod_backend.entity.Bodega;
import com.example.minimal_prod_backend.entity.TipoBodega;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BodegaMapper {

    @Mappings({
            @Mapping(source = "tipo", target = "tipo")
    })
    BodegaResponse toResponse(Bodega entity);

    Bodega toEntity(BodegaInput dto);

    default TipoBodegaResponse toTipoResponse(TipoBodega tipo) {
        if (tipo == null) return null;
        TipoBodegaResponse dto = new TipoBodegaResponse();
        dto.setId(tipo.getId());
        dto.setCodigo(tipo.getCodigo());
        dto.setNombre(tipo.getNombre());
        dto.setDescripcion(tipo.getDescripcion());
        return dto;
    }
}
