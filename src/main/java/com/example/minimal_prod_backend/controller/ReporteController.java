package com.example.minimal_prod_backend.controller;

import com.example.minimal_prod_backend.dto.IndicadorProductividadDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoMaterialDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoOrdenDTO;
import com.example.minimal_prod_backend.service.ReporteService;
import com.example.minimal_prod_backend.util.CsvGenerator;
import com.example.minimal_prod_backend.util.PdfGenerator;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    // --- Indicadores de productividad
    @GetMapping("/productividad")
    public ResponseEntity<List<IndicadorProductividadDTO>> getProductividad() {
        return ResponseEntity.ok(service.obtenerProductividad());
    }

    @GetMapping("/productividad/pdf")
    public ResponseEntity<InputStreamResource> getProductividadPdf() {
        var pdf = PdfGenerator.generarProductividadPDF(service.obtenerProductividad());
        return buildPdfResponse(pdf, "indicadores_productividad.pdf");
    }

    // --- Reporte de costos por orden
    @GetMapping("/costos/orden")
    public ResponseEntity<List<ReporteCostoOrdenDTO>> getCostosOrden() {
        return ResponseEntity.ok(service.obtenerCostosPorOrden());
    }

    @GetMapping("/costos/orden/pdf")
    public ResponseEntity<InputStreamResource> getCostosOrdenPdf() {
        var pdf = PdfGenerator.generarCostosOrdenPDF(service.obtenerCostosPorOrden());
        return buildPdfResponse(pdf, "reporte_costos_orden.pdf");
    }

    @GetMapping("/costos/orden/csv")
    public ResponseEntity<InputStreamResource> getCostosOrdenCsv() {
        var csv = CsvGenerator.generarCostosOrdenCSV(service.obtenerCostosPorOrden());
        return buildCsvResponse(csv, "reporte_costos_orden.csv");
    }

    // --- Reporte de costos por material
    @GetMapping("/costos/material")
    public ResponseEntity<List<ReporteCostoMaterialDTO>> getCostosMaterial() {
        return ResponseEntity.ok(service.obtenerCostosPorMaterial());
    }

    // Helpers
    private ResponseEntity<InputStreamResource> buildPdfResponse(ByteArrayInputStream stream, String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }

    private ResponseEntity<InputStreamResource> buildCsvResponse(ByteArrayInputStream stream, String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(stream));
    }
}
