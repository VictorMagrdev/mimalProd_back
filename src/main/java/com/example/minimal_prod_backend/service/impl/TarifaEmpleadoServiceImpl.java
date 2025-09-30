package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.TarifaEmpleadoRequest;
import com.example.minimal_prod_backend.dto.TarifaEmpleadoResponse;
import com.example.minimal_prod_backend.entity.TarifaEmpleado;
import com.example.minimal_prod_backend.entity.Usuario;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.TarifaEmpleadoMapper;
import com.example.minimal_prod_backend.repository.TarifaEmpleadoRepository;
import com.example.minimal_prod_backend.repository.UserRepository;
import com.example.minimal_prod_backend.service.TarifaEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarifaEmpleadoServiceImpl implements TarifaEmpleadoService {

    private final TarifaEmpleadoRepository tarifaEmpleadoRepository;
    private final UserRepository userRepository;
    private final TarifaEmpleadoMapper tarifaEmpleadoMapper;

    @Override
    public List<TarifaEmpleadoResponse> getTarifasEmpleado() {
        return tarifaEmpleadoRepository.findAll().stream()
                .map(tarifaEmpleadoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TarifaEmpleadoResponse getTarifaEmpleadoById(Long id) {
        TarifaEmpleado tarifa = tarifaEmpleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TarifaEmpleado not found with id: " + id));
        return tarifaEmpleadoMapper.toResponse(tarifa);
    }

    @Override
    public TarifaEmpleadoResponse createTarifaEmpleado(TarifaEmpleadoRequest request) {
        Usuario usuario = userRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id: " + request.usuarioId()));

        TarifaEmpleado tarifa = tarifaEmpleadoMapper.toEntity(request);
        tarifa.setUsuario(usuario);

        return tarifaEmpleadoMapper.toResponse(tarifaEmpleadoRepository.save(tarifa));
    }

    @Override
    public TarifaEmpleadoResponse updateTarifaEmpleado(Long id, TarifaEmpleadoRequest request) {
        TarifaEmpleado existingTarifa = tarifaEmpleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TarifaEmpleado not found with id: " + id));

        Usuario usuario = userRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id: " + request.usuarioId()));

        existingTarifa.setUsuario(usuario);
        existingTarifa.setTarifa(request.tarifa());
        existingTarifa.setMoneda(request.moneda());
        existingTarifa.setVigenteDesde(request.vigenteDesde());
        existingTarifa.setVigenteHasta(request.vigenteHasta());

        return tarifaEmpleadoMapper.toResponse(tarifaEmpleadoRepository.save(existingTarifa));
    }

    @Override
    public void deleteTarifaEmpleado(Long id) {
        if (!tarifaEmpleadoRepository.existsById(id)) {
            throw new ResourceNotFoundException("TarifaEmpleado not found with id: " + id);
        }
        tarifaEmpleadoRepository.deleteById(id);
    }
}
