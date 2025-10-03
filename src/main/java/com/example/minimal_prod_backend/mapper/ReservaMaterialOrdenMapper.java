package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.ReservaMaterialOrdenRequest;
import com.example.minimal_prod_backend.dto.ReservaMaterialOrdenResponse;
import com.example.minimal_prod_backend.entity.ReservaMaterialOrden;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMaterialOrdenMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "ordenId", target = "orden.id"),
            @Mapping(source = "productoId", target = "producto.id"),
            @Mapping(source = "loteId", target = "lote.id"),
            @Mapping(source = "unidadId", target = "unidad.id"),
            @Mapping(source = "fechaReserva", target = "fechaReserva")
    })
    ReservaMaterialOrden toEntity(ReservaMaterialOrdenRequest input);

    List<ReservaMaterialOrdenResponse> toResponseList(List<ReservaMaterialOrden> conteos);

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
            @Mapping(source = "unidadId", target = "unidad.id"),
            @Mapping(source = "fechaReserva", target = "fechaReserva")
    })
    void updateEntityFromInput(ReservaMaterialOrdenRequest input, @MappingTarget ReservaMaterialOrden entity);
}
