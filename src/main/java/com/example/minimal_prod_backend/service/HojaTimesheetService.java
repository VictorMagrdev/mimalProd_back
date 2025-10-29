package com.example.minimal_prod_backend.service;

import com.example.minimal_prod_backend.dto.HojaTimesheetRequest;
import com.example.minimal_prod_backend.dto.HojaTimesheetResponse;

import java.util.List;

public interface HojaTimesheetService {
    List<HojaTimesheetResponse> findAll();

    HojaTimesheetResponse findById(Long id);

    HojaTimesheetResponse save(HojaTimesheetRequest hojaTimesheetRequest);

    void deleteById(Long id);
}
