package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.*;
import com.example.minimal_prod_backend.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdenProduccionMapper {

    OrdenProduccionResponse toResponse(OrdenProduccion entity);

    List<OrdenProduccionResponse> toResponseList(List<OrdenProduccion> entities);

    OrdenProduccion toEntity(OrdenProduccionInput dto);

    LoteProduccionResponse toLoteResponse(LoteProduccion lote);

    ProductoResponse toProductoResponse(Producto producto);

    UnidadMedidaResponse toUnidadResponse(UnidadMedida unidad);

    EstadoOrdenResponse toEstadoResponse(EstadoOrden estado);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromInput(OrdenProduccionInput dto, @MappingTarget OrdenProduccion entity);
}
