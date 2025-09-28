package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.ConteoCiclicoInput;
import com.example.minimal_prod_backend.dto.ConteoCiclicoResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.ConteoCiclicoMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.ConteoCiclicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConteoCiclicoServiceImpl implements ConteoCiclicoService {

    private final ConteoCiclicoRepository conteoCiclicoRepository;
    private final ProductoRepository productoRepository;
    private final BodegaRepository bodegaRepository;
    private final LoteProduccionRepository loteProduccionRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ConteoCiclicoMapper mapper;

    @Override
    public List<ConteoCiclicoResponse> getConteosCiclicos() {
        return mapper.toResponseList(conteoCiclicoRepository.findAll());
    }

    @Override
    public ConteoCiclicoResponse getConteoCiclicoById(Long id) {
        ConteoCiclico conteoCiclico = conteoCiclicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConteoCiclico not found with id: " + id));
        return mapper.toResponse(conteoCiclico);
    }

    @Override
    public ConteoCiclicoResponse createConteoCiclico(ConteoCiclicoInput input) {
        ConteoCiclico entity = mapper.toEntity(input);

        attachRelations(input, entity);

        ConteoCiclico saved = conteoCiclicoRepository.save(entity);

        return mapper.toResponse(saved);
    }

    @Override
    public ConteoCiclicoResponse updateConteoCiclico(Long id, ConteoCiclicoInput input) {
        ConteoCiclico existing = conteoCiclicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConteoCiclico not found with id: " + id));

        mapper.updateEntityFromInput(input, existing);

        attachRelations(input, existing);

        ConteoCiclico saved = conteoCiclicoRepository.save(existing);

        return mapper.toResponse(saved);
    }

    @Override
    public boolean deleteConteoCiclico(Long id) {
        if (!conteoCiclicoRepository.existsById(id)) {
            return false;
        }
        conteoCiclicoRepository.deleteById(id);
        return true;
    }

    private void attachRelations(ConteoCiclicoInput dto, ConteoCiclico entity) {
        if (dto.getProductoId() != null) {
            Producto producto = productoRepository.findById(dto.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto not found with id: " + dto.getProductoId()));
            entity.setProducto(producto);
        }

        if (dto.getBodegaId() != null) {
            Bodega bodega = bodegaRepository.findById(dto.getBodegaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bodega not found with id: " + dto.getBodegaId()));
            entity.setBodega(bodega);
        }

        if (dto.getLoteId() != null) {
            LoteProduccion lote = loteProduccionRepository.findById(dto.getLoteId())
                    .orElseThrow(() -> new ResourceNotFoundException("LoteProduccion not found with id: " + dto.getLoteId()));
            entity.setLote(lote);
        }

        if (dto.getUnidadId() != null) {
            UnidadMedida unidad = unidadMedidaRepository.findById(dto.getUnidadId())
                    .orElseThrow(() -> new ResourceNotFoundException("UnidadMedida not found with id: " + dto.getUnidadId()));
            entity.setUnidad(unidad);
        }
    }
}
