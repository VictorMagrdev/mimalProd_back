package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.ReservaMaterialOrdenInput;
import com.example.minimal_prod_backend.dto.ReservaMaterialOrdenResponse;
import com.example.minimal_prod_backend.entity.ReservaMaterialOrden;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ReservaMaterialOrdenMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id"),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "loteId", target = "lote.id"),
            @Mapping(source = "unidadId", target = "unidad.id")
    })
    ReservaMaterialOrden toEntity(ReservaMaterialOrdenInput input);

    @Mappings({
        @Mapping(source = "orden.id", target = "ordenId"),
        @Mapping(source = "producto.id", target = "productoId"),
        @Mapping(source = "lote.id", target = "loteId"),
        @Mapping(source = "unidad.id", target = "unidadId")
    })
    ReservaMaterialOrdenResponse toResponse(ReservaMaterialOrden entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id"),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "loteId", target = "lote.id"),
            @Mapping(source = "unidadId", target = "unidad.id")
    })
    void updateEntityFromInput(ReservaMaterialOrdenInput input, @MappingTarget ReservaMaterialOrden entity);
}
