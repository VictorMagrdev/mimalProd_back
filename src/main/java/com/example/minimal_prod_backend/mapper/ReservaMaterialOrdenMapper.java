package com.example.minimal_prod_backend.mapper;

import com.example.minimal_prod_backend.dto.ReservaMaterialOrdenInput;
import com.example.minimal_prod_backend.dto.ReservaMaterialOrdenResponse;
import com.example.minimal_prod_backend.entity.ReservaMaterialOrden;
import com.example.minimal_prod_backend.repository.LoteProduccionRepository;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        OrdenProduccionRepository.class,
        ProductoRepository.class,
        LoteProduccionRepository.class,
        UnidadMedidaRepository.class
})
public abstract class ReservaMaterialOrdenMapper {

    @Autowired
    protected OrdenProduccionRepository ordenProduccionRepository;
    @Autowired
    protected ProductoRepository productoRepository;
    @Autowired
    protected LoteProduccionRepository loteProduccionRepository;
    @Autowired
    protected UnidadMedidaRepository unidadMedidaRepository;

    public abstract ReservaMaterialOrdenResponse toResponse(ReservaMaterialOrden entity);

    public abstract List<ReservaMaterialOrdenResponse> toResponseList(List<ReservaMaterialOrden> entities);

    @Mapping(target = "orden", expression = "java(dto.getIdOrden() != null ? ordenProduccionRepository.findById(dto.getIdOrden())" +
            ".orElseThrow(() -> new com.example.minimal_prod_backend.exception.ResourceNotFoundException(\"OrdenProduccion not found with id: \" + dto.getIdOrden())) : null)")
    @Mapping(target = "producto", expression = "java(dto.getIdProducto() != null ? productoRepository.findById(dto.getIdProducto())" +
            ".orElseThrow(() -> new com.example.minimal_prod_backend.exception.ResourceNotFoundException(\"Producto not found with id: \" + dto.getIdProducto())) : null)")
    @Mapping(target = "lote", expression = "java(dto.getIdLote() != null ? loteProduccionRepository.findById(dto.getIdLote())" +
            ".orElseThrow(() -> new com.example.minimal_prod_backend.exception.ResourceNotFoundException(\"LoteProduccion not found with id: \" + dto.getIdLote())) : null)")
    @Mapping(target = "unidad", expression = "java(dto.getIdUnidad() != null ? unidadMedidaRepository.findById(dto.getIdUnidad())" +
            ".orElseThrow(() -> new com.example.minimal_prod_backend.exception.ResourceNotFoundException(\"UnidadMedida not found with id: \" + dto.getIdUnidad())) : null)")
    public abstract ReservaMaterialOrden toEntity(ReservaMaterialOrdenInput dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromInput(ReservaMaterialOrdenInput dto, @MappingTarget ReservaMaterialOrden entity);
}
