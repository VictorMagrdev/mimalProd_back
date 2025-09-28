package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.LineaOrdenInput;
import com.example.minimal_prod_backend.dto.LineaOrdenResponse;
import com.example.minimal_prod_backend.entity.LineaOrden;
import com.example.minimal_prod_backend.entity.OrdenProduccion;
import com.example.minimal_prod_backend.entity.Producto;
import com.example.minimal_prod_backend.entity.UnidadMedida;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.repository.LineaOrdenRepository;
import com.example.minimal_prod_backend.repository.OrdenProduccionRepository;
import com.example.minimal_prod_backend.repository.ProductoRepository;
import com.example.minimal_prod_backend.repository.UnidadMedidaRepository;
import com.example.minimal_prod_backend.service.LineaOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineaOrdenServiceImpl implements LineaOrdenService {

    private final LineaOrdenRepository lineaOrdenRepository;
    private final OrdenProduccionRepository ordenProduccionRepository;
    private final ProductoRepository productoRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final LineaOrdenMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<LineaOrdenResponse> getLineasOrden() {
        return mapper.toResponseList(lineaOrdenRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public LineaOrdenResponse getLineaOrdenById(Long id) {
        LineaOrden lineaOrden = lineaOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LineaOrden not found with id: " + id));
        return mapper.toResponse(lineaOrden);
    }

    @Override
    @Transactional
    public LineaOrdenResponse createLineaOrden(LineaOrdenInput input) {
        LineaOrden entity = mapper.toEntity(input);
        attachRelations(input, entity);
        LineaOrden saved = lineaOrdenRepository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public LineaOrdenResponse updateLineaOrden(Long id, LineaOrdenInput input) {
        LineaOrden existing = lineaOrdenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LineaOrden not found with id: " + id));

        mapper.updateEntityFromInput(input, existing);
        attachRelations(input, existing);

        return mapper.toResponse(lineaOrdenRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteLineaOrden(Long id) {
        lineaOrdenRepository.deleteById(id);
    }

    private void attachRelations(LineaOrdenInput dto, LineaOrden entity) {
        if (dto.getIdOrden() != null) {
            OrdenProduccion orden = ordenProduccionRepository.findById(dto.getIdOrden())
                    .orElseThrow(() -> new ResourceNotFoundException("OrdenProduccion not found"));
            entity.setOrden(orden);
        }
        if (dto.getIdProductoComponente() != null) {
            Producto producto = productoRepository.findById(dto.getIdProductoComponente())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found"));
            entity.setProductoComponente(producto);
        }
        if (dto.getIdUnidadComponente() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getIdUnidadComponente())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found"));
            entity.setUnidadComponente(unidad);
        }
    }
}
