package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.RegistroTiempoRequest;
import com.example.minimal_prod_backend.dto.RegistroTiempoResponse;
import com.example.minimal_prod_backend.entity.*;
import com.example.minimal_prod_backend.exception.ResourceNotFoundException;
import com.example.minimal_prod_backend.mapper.RegistroTiempoMapper;
import com.example.minimal_prod_backend.repository.*;
import com.example.minimal_prod_backend.service.RegistroTiempoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroTiempoServiceImpl implements RegistroTiempoService {

    private final RegistroTiempoRepository registroTiempoRepository;
    private final AsignacionesRepository asignacionRepository;
    private final TipoActividadRepository tipoActividadRepository;
    private final TipoCostoRepository tipoCostoRepository;
    private final EstadoAprobacionRepository estadoAprobacionRepository;
    private final HojaTimesheetRepository hojaTimesheetRepository;
    private final RegistroTiempoMapper registroTiempoMapper;

    @Override
    public List<RegistroTiempoResponse> getRegistrosTiempo() {
        return registroTiempoRepository.findAll().stream()
                .map(registroTiempoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RegistroTiempoResponse getRegistroTiempoById(Long id) {
        RegistroTiempo registro = registroTiempoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RegistroTiempo not found with id: " + id));
        return registroTiempoMapper.toResponse(registro);
    }

    @Override
    public RegistroTiempoResponse createRegistroTiempo(RegistroTiempoRequest request) {
        Asignacion asignacion = asignacionRepository.findById(request.asignacionId())
                .orElseThrow(() -> new ResourceNotFoundException("Asignacion not found with id: " + request.asignacionId()));
        TipoActividad tipoActividad = tipoActividadRepository.findById(request.tipoActividadId())
                .orElseThrow(() -> new ResourceNotFoundException("TipoActividad not found with id: " + request.tipoActividadId()));
        TipoCosto tipoCosto = tipoCostoRepository.findById(request.tipoCostoId())
                .orElseThrow(() -> new ResourceNotFoundException("TipoCosto not found with id: " + request.tipoCostoId()));
        EstadoAprobacion estadoAprobacion = estadoAprobacionRepository.findById(request.estadoAprobacionId())
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAprobacion not found with id: " + request.estadoAprobacionId()));
        HojaTimesheet hojaTimesheet = hojaTimesheetRepository.findById(request.hojaTimesheetId())
                .orElseThrow(() -> new ResourceNotFoundException("HojaTimesheet not found with id: " + request.hojaTimesheetId()));

        RegistroTiempo registro = registroTiempoMapper.toEntity(request);
        registro.setAsignacion(asignacion);
        registro.setTipoActividad(tipoActividad);
        registro.setTipoCosto(tipoCosto);
        registro.setEstadoAprobacion(estadoAprobacion);
        registro.setHojaTimesheet(hojaTimesheet);

        return registroTiempoMapper.toResponse(registroTiempoRepository.save(registro));
    }

    @Override
    public RegistroTiempoResponse updateRegistroTiempo(Long id, RegistroTiempoRequest request) {
        RegistroTiempo existingRegistro = registroTiempoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RegistroTiempo not found with id: " + id));

        Asignacion asignacion = asignacionRepository.findById(request.asignacionId())
                .orElseThrow(() -> new ResourceNotFoundException("Asignacion not found with id: " + request.asignacionId()));
        TipoActividad tipoActividad = tipoActividadRepository.findById(request.tipoActividadId())
                .orElseThrow(() -> new ResourceNotFoundException("TipoActividad not found with id: " + request.tipoActividadId()));
        TipoCosto tipoCosto = tipoCostoRepository.findById(request.tipoCostoId())
                .orElseThrow(() -> new ResourceNotFoundException("TipoCosto not found with id: " + request.tipoCostoId()));
        EstadoAprobacion estadoAprobacion = estadoAprobacionRepository.findById(request.estadoAprobacionId())
                .orElseThrow(() -> new ResourceNotFoundException("EstadoAprobacion not found with id: " + request.estadoAprobacionId()));
        HojaTimesheet hojaTimesheet = hojaTimesheetRepository.findById(request.hojaTimesheetId())
                .orElseThrow(() -> new ResourceNotFoundException("HojaTimesheet not found with id: " + request.hojaTimesheetId()));

        existingRegistro.setAsignacion(asignacion);
        existingRegistro.setInicioTz(request.inicioTz());
        existingRegistro.setFinTz(request.finTz());
        existingRegistro.setTipoActividad(tipoActividad);
        existingRegistro.setTipoCosto(tipoCosto);
        existingRegistro.setEstadoAprobacion(estadoAprobacion);
        existingRegistro.setHojaTimesheet(hojaTimesheet);

        return registroTiempoMapper.toResponse(registroTiempoRepository.save(existingRegistro));
    }

    @Override
    public void deleteRegistroTiempo(Long id) {
        if (!registroTiempoRepository.existsById(id)) {
            throw new ResourceNotFoundException("RegistroTiempo not found with id: " + id);
        }
        registroTiempoRepository.deleteById(id);
    }
}
