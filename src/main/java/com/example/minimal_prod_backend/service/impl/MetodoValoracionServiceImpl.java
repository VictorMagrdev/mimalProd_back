package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.MetodoValoracionRequest;
import com.example.minimal_prod_backend.dto.Response.MetodoValoracionResponse;
import com.example.minimal_prod_backend.entity.MetodoValoracion;
import com.example.minimal_prod_backend.repository.MetodoValoracionRepository;
import com.example.minimal_prod_backend.service.MetodoValoracionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetodoValoracionServiceImpl implements MetodoValoracionService {

    private final MetodoValoracionRepository metodoValoracionRepository;

    @Override
    public List<MetodoValoracion> findAll() {
        return (List<MetodoValoracion>) metodoValoracionRepository.findAll();
    }

    @Override
    public MetodoValoracion findById(Long id) {
        return metodoValoracionRepository.findById(id).orElse(null);
    }
    @Override
    public MetodoValoracionResponse GetById(Long id) {
        return metodoValoracionRepository.findById(id)
                .map(m -> new MetodoValoracionResponse(
                        m.getId(),
                        m.getCodigo(),
                        m.getNombre(),
                        m.getDescripcion()
                ))
                .orElse(null);
    }
    @Override
    public MetodoValoracion save(MetodoValoracionRequest input) {
        MetodoValoracion metodoValoracion = new MetodoValoracion();
        metodoValoracion.setCodigo(input.codigo());
        metodoValoracion.setNombre(input.nombre());
        metodoValoracion.setDescripcion(input.descripcion());
        return metodoValoracionRepository.save(metodoValoracion);
    }

    @Override
    public MetodoValoracion update(Long id, MetodoValoracionRequest input) {
        MetodoValoracion metodoValoracion = metodoValoracionRepository.findById(id).orElse(null);
        if (metodoValoracion != null) {
            metodoValoracion.setCodigo(input.codigo());
            metodoValoracion.setNombre(input.nombre());
            metodoValoracion.setDescripcion(input.descripcion());
            return metodoValoracionRepository.save(metodoValoracion);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        metodoValoracionRepository.deleteById(id);
    }
}
