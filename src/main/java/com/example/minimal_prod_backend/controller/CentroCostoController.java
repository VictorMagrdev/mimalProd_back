package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.CentroCostoRequest;
import com.example.minimal_prod_backend.dto.CentroCostoResponse;
import com.example.minimal_prod_backend.service.CentroCostoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/centros-costo")
@RequiredArgsConstructor
public class CentroCostoController {

    private final CentroCostoService centroCostoService;

    @GetMapping
    public ResponseEntity<List<CentroCostoResponse>> getCentrosDeCosto() {
        return ResponseEntity.ok(centroCostoService.getCentrosDeCosto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroCostoResponse> getCentroDeCostoById(@PathVariable Long id) {
        return ResponseEntity.ok(centroCostoService.getCentroDeCostoById(id));
    }

    @PostMapping
    public ResponseEntity<CentroCostoResponse> createCentroDeCosto(@RequestBody CentroCostoRequest centroCostoInput) {
        return new ResponseEntity<>(centroCostoService.createCentroDeCosto(centroCostoInput), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroCostoResponse> updateCentroDeCosto(@PathVariable Long id, @RequestBody CentroCostoRequest centroCostoInput) {
        return ResponseEntity.ok(centroCostoService.updateCentroDeCosto(id, centroCostoInput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentroDeCosto(@PathVariable Long id) {
        centroCostoService.deleteCentroDeCosto(id);
        return ResponseEntity.noContent().build();
    }
}
