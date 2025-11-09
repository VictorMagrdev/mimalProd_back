package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.FuncionTareaRequest;
import com.example.minimal_prod_backend.dto.Response.FuncionTareaResponse;
import com.example.minimal_prod_backend.service.FuncionTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FuncionTareaController {

    @Autowired
    private FuncionTareaService funcionTareaService;

    @QueryMapping
    public List<FuncionTareaResponse> funcionesTarea() {
        return funcionTareaService.findAll();
    }

    @QueryMapping
    public FuncionTareaResponse funcionTareaById(@Argument Long id) {
        return funcionTareaService.findById(id);
    }


    @MutationMapping
    public FuncionTareaResponse createFuncionTarea(@Argument FuncionTareaRequest input) {
        return funcionTareaService.save(input);
    }

    @MutationMapping
    public Boolean eliminarFuncionTarea(@Argument Long id) {
        funcionTareaService.deleteById(id);
        return true;
    }
}
