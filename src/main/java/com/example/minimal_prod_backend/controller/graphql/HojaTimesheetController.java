package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.HojaTimesheetRequest;
import com.example.minimal_prod_backend.dto.Response.HojaTimesheetResponse;
import com.example.minimal_prod_backend.service.HojaTimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class HojaTimesheetController {

    @Autowired
    private HojaTimesheetService hojaTimesheetService;

    @QueryMapping
    public List<HojaTimesheetResponse> hojasTimesheet() {
        return hojaTimesheetService.findAll();
    }

    @QueryMapping
    public HojaTimesheetResponse hojaTimesheetById(@Argument Long id) {
        return hojaTimesheetService.findById(id);
    }

    @MutationMapping
    public HojaTimesheetResponse createHojaTimesheet(@Argument HojaTimesheetRequest input) {
        return hojaTimesheetService.save(input);
    }

    @MutationMapping
    public Boolean eliminarHojaTimesheet(@Argument Long id) {
        hojaTimesheetService.deleteById(id);
        return true;
    }
}
