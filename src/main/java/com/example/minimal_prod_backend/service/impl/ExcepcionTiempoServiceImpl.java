package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.Request.ExcepcionTiempoRequest;
import com.example.minimal_prod_backend.dto.Response.ExcepcionTiempoResponse;
import com.example.minimal_prod_backend.entity.ExcepcionTiempo;
import com.example.minimal_prod_backend.entity.Usuario;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.ExcepcionTiempoMapper;
import com.example.minimal_prod_backend.repository.ExcepcionTiempoRepository;
import com.example.minimal_prod_backend.repository.UserRepository;
import com.example.minimal_prod_backend.service.ExcepcionTiempoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcepcionTiempoServiceImpl implements ExcepcionTiempoService {

    private final ExcepcionTiempoRepository excepcionTiempoRepository;
    private final UserRepository userRepository;
    private final ExcepcionTiempoMapper excepcionTiempoMapper;

    @Override
    public List<ExcepcionTiempoResponse> getExcepcionesTiempo() {
        return excepcionTiempoRepository.findAll().stream()
                .map(excepcionTiempoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ExcepcionTiempoResponse getExcepcionTiempoById(Long id) {
        ExcepcionTiempo excepcion = excepcionTiempoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExcepcionTiempo not found with id: " + id));
        return excepcionTiempoMapper.toResponse(excepcion);
    }

    @Override
    public ExcepcionTiempoResponse createExcepcionTiempo(ExcepcionTiempoRequest request) {
        Usuario usuario = userRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id: " + request.usuarioId()));

        ExcepcionTiempo excepcion = excepcionTiempoMapper.toEntity(request);
        excepcion.setUsuario(usuario);

        return excepcionTiempoMapper.toResponse(excepcionTiempoRepository.save(excepcion));
    }

    @Override
    public ExcepcionTiempoResponse updateExcepcionTiempo(Long id, ExcepcionTiempoRequest request) {
        ExcepcionTiempo existingExcepcion = excepcionTiempoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExcepcionTiempo not found with id: " + id));

        Usuario usuario = userRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id: " + request.usuarioId()));

        existingExcepcion.setUsuario(usuario);
        existingExcepcion.setPeriodo(request.periodo());
        existingExcepcion.setDetalles(request.detalles());

        return excepcionTiempoMapper.toResponse(excepcionTiempoRepository.save(existingExcepcion));
    }

    @Override
    public void deleteExcepcionTiempo(Long id) {
        if (!excepcionTiempoRepository.existsById(id)) {
            throw new ResourceNotFoundException("ExcepcionTiempo not found with id: " + id);
        }
        excepcionTiempoRepository.deleteById(id);
    }

    @Override
    public ExcepcionTiempoResponse resolveExcepcionTiempo(Long id) {
        ExcepcionTiempo existingExcepcion = excepcionTiempoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExcepcionTiempo not found with id: " + id));
        existingExcepcion.setResuelto(true);
        return excepcionTiempoMapper.toResponse(excepcionTiempoRepository.save(existingExcepcion));
    }
}
