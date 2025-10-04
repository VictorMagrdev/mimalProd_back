package com.example.minimal_prod_backend.service.impl;

import com.example.minimal_prod_backend.dto.HojaTimesheetRequest;
import com.example.minimal_prod_backend.dto.HojaTimesheetResponse;
import com.example.minimal_prod_backend.entity.HojaTimesheet;
import com.example.minimal_prod_backend.mapper.HojaTimesheetMapper;
import com.example.minimal_prod_backend.repository.HojaTimesheetRepository;
import com.example.minimal_prod_backend.service.HojaTimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HojaTimesheetServiceImpl implements HojaTimesheetService {

    @Autowired
    private HojaTimesheetRepository hojaTimesheetRepository;

    @Autowired
    private HojaTimesheetMapper hojaTimesheetMapper;

    @Override
    public List<HojaTimesheetResponse> findAll() {
        return hojaTimesheetRepository.findAll().stream()
                .map(hojaTimesheetMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HojaTimesheetResponse findById(Long id) {
        HojaTimesheet hojaTimesheet = hojaTimesheetRepository.findById(id).orElse(null);
        return hojaTimesheetMapper.toResponse(hojaTimesheet);
    }

    @Override
    public HojaTimesheetResponse save(HojaTimesheetRequest hojaTimesheetRequest) {
        HojaTimesheet hojaTimesheet = hojaTimesheetMapper.toEntity(hojaTimesheetRequest);
        return hojaTimesheetMapper.toResponse(hojaTimesheetRepository.save(hojaTimesheet));
    }

    @Override
    public void deleteById(Long id) {
        hojaTimesheetRepository.deleteById(id);
    }
}
