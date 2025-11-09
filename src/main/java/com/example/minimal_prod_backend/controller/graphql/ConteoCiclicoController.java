package com.example.minimal_prod_backend.controller.graphql;

import com.example.minimal_prod_backend.dto.Request.ConteoCiclicoRequest;
import com.example.minimal_prod_backend.dto.Response.*;
import com.example.minimal_prod_backend.service.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ConteoCiclicoController {

    private final ConteoCiclicoService conteoCiclicoService;
    private final ProductoService productoService;
    private final BodegaService bodegaService;
    private final LoteProduccionService loteProduccionService;
    private final UnidadMedidaService unidadMedidaService;

    public ConteoCiclicoController(ConteoCiclicoService conteoCiclicoService,
                                   ProductoService productoService,
                                   BodegaService bodegaService,
                                   LoteProduccionService loteProduccionService,
                                   UnidadMedidaService unidadMedidaService) {
        this.conteoCiclicoService = conteoCiclicoService;
        this.productoService = productoService;
        this.bodegaService = bodegaService;
        this.loteProduccionService = loteProduccionService;
        this.unidadMedidaService = unidadMedidaService;
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('CONTEOS_CICLICO_TAG', 'READ')")
    public List<ConteoCiclicoResponse> conteosCiclicos() {
        return conteoCiclicoService.getConteosCiclicos();
    }

    @QueryMapping
    @PreAuthorize("@customSecurity.hasPermission('CONTEOS_CICLICO_TAG', 'READ')")
    public ConteoCiclicoResponse conteoCiclico(@Argument Long id) {
        return conteoCiclicoService.getConteoCiclicoById(id);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('CONTEOS_CICLICO_TAG', 'CREATE')")
    public ConteoCiclicoResponse createConteoCiclico(@Argument("input") ConteoCiclicoRequest conteoCiclicoInput) {
        return conteoCiclicoService.createConteoCiclico(conteoCiclicoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('CONTEOS_CICLICO_TAG', 'UPDATE')")
    public ConteoCiclicoResponse updateConteoCiclico(@Argument Long id, @Argument("input") ConteoCiclicoRequest conteoCiclicoInput) {
        return conteoCiclicoService.updateConteoCiclico(id, conteoCiclicoInput);
    }

    @MutationMapping
    @PreAuthorize("@customSecurity.hasPermission('CONTEOS_CICLICO_TAG', 'DELETE')")
    public boolean deleteConteoCiclico(@Argument Long id) {
        return conteoCiclicoService.deleteConteoCiclico(id);
    }

    @SchemaMapping(typeName = "ConteoCiclico", field = "producto")
    public ProductoResponse producto(ConteoCiclicoResponse conteoResponse) {
        if (conteoResponse.productoId() == null) {
            return null;
        }
        return productoService.getProductoById(conteoResponse.productoId());
    }

    @SchemaMapping(typeName = "ConteoCiclico", field = "bodega")
    public BodegaResponse bodega(ConteoCiclicoResponse conteoResponse) {
        if (conteoResponse.bodegaId() == null) {
            return null;
        }
        return bodegaService.getBodegaById(conteoResponse.bodegaId());
    }

    @SchemaMapping(typeName = "ConteoCiclico", field = "lote")
    public LoteProduccionResponse lote(ConteoCiclicoResponse conteoResponse) {
        if (conteoResponse.loteId() == null) {
            return null;
        }
        return loteProduccionService.getLoteProduccionById(conteoResponse.loteId());
    }

    @SchemaMapping(typeName = "ConteoCiclico", field = "unidad")
    public UnidadMedidaResponse unidad(ConteoCiclicoResponse conteoResponse) {
        if (conteoResponse.unidadId() == null) {
            return null;
        }
        return unidadMedidaService.getUnidadMedidaById(conteoResponse.unidadId());
    }
}