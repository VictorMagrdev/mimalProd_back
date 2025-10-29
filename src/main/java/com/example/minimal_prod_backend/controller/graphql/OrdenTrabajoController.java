package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.OrdenTrabajoRequest;
import com.example.minimal_prod_backend.dto.OrdenTrabajoResponse;
import com.example.minimal_prod_backend.service.OrdenTrabajoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrdenTrabajoController {
    private static final Logger logger = LoggerFactory.getLogger(OrdenTrabajoController.class);

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @QueryMapping
    public List<OrdenTrabajoResponse> ordenesTrabajo() {
        return ordenTrabajoService.findAll();
    }

    @QueryMapping
    public OrdenTrabajoResponse ordenTrabajoById(@Argument Long id) {
        return ordenTrabajoService.findById(id);
    }

    @MutationMapping
    public OrdenTrabajoResponse createOrdenTrabajo(@Argument OrdenTrabajoRequest input) {
        logger.debug("1");
        return ordenTrabajoService.save(input);
    }

    @MutationMapping
    public Boolean eliminarOrdenTrabajo(@Argument Long id) {
        ordenTrabajoService.deleteById(id);
        return true;
    }
}
