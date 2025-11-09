package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.Request.ReservaMaterialOrdenRequest;
import com.example.minimal_prod_backend.dto.Response.ReservaMaterialOrdenResponse;
import com.example.minimal_prod_backend.entity.ReservaMaterialOrden;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMaterialOrdenMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "orden", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "lote", ignore = true),
            @Mapping(target = "unidad", ignore = true),
            @Mapping(source = "fechaReserva", target = "fechaReserva")
    })
    ReservaMaterialOrden toEntity(ReservaMaterialOrdenRequest input);

    List<ReservaMaterialOrdenResponse> toResponseList(List<ReservaMaterialOrden> entities);

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
            @Mapping(target = "orden", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "lote", ignore = true),
            @Mapping(target = "unidad", ignore = true),
            @Mapping(source = "fechaReserva", target = "fechaReserva")
    })
    void updateEntityFromInput(ReservaMaterialOrdenRequest input, @MappingTarget ReservaMaterialOrden entity);
}
