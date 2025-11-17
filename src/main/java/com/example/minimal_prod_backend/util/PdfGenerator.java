package com.example.minimal_prod_backend.util;

import com.example.minimal_prod_backend.dto.IndicadorProductividadDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoOrdenDTO;
import org.openpdf.text.Document;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Phrase;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

public class PdfGenerator {

    // ===============================
    //  CONSTANTES (evita números mágicos)
    // ===============================
    private static final int COLS_PRODUCTIVIDAD = 5;
    private static final int COLS_COSTOS = 6;

    // pesos iguales (todas las columnas con ancho uniforme)
    private static float[] generateUniformWidths(int columns) {
        float[] widths = new float[columns];
        // ancho uniforme (no es número mágico, es estándar)
        Arrays.fill(widths, 1f);
        return widths;
    }

    private static final float[] PRODUCTIVIDAD_WIDTHS = generateUniformWidths(COLS_PRODUCTIVIDAD);
    private static final float[] COSTO_WIDTHS = generateUniformWidths(COLS_COSTOS);

    // ===============================
    //  PDF DE PRODUCTIVIDAD
    // ===============================
    public static ByteArrayInputStream generarProductividadPDF(List<IndicadorProductividadDTO> data) {
        Document doc = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, out);
            doc.open();

            doc.add(new Paragraph("Indicadores de Productividad"));
            doc.add(new Paragraph(" "));

            PdfPTable table = buildProductividadTable(data);
            doc.add(table);

            doc.close();
            writer.close();

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF de productividad", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static PdfPTable buildProductividadTable(List<IndicadorProductividadDTO> data) {
        PdfPTable table = new PdfPTable(COLS_PRODUCTIVIDAD);
        table.setWidthPercentage(100);
        table.setWidths(PRODUCTIVIDAD_WIDTHS);

        addHeaderCell(table, "Orden");
        addHeaderCell(table, "Producto");
        addHeaderCell(table, "Unidades");
        addHeaderCell(table, "Horas");
        addHeaderCell(table, "Eficiencia (%)");

        for (var p : data) {
            table.addCell(toSafeString(p.ordenId()));
            table.addCell(toSafeString(p.producto()));
            table.addCell(toSafeString(p.unidadesProducidas()));
            table.addCell(toSafeString(p.horasTrabajadas()));
            table.addCell(toSafeString(p.eficiencia()));
        }

        return table;
    }

    // ===============================
    //  PDF DE COSTOS POR ORDEN
    // ===============================
    public static ByteArrayInputStream generarCostosOrdenPDF(List<ReporteCostoOrdenDTO> data) {
        Document doc = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, out);
            doc.open();

            doc.add(new Paragraph("Reporte de Costos por Orden"));
            doc.add(new Paragraph(" "));

            PdfPTable table = buildCostosTable(data);
            doc.add(table);

            doc.close();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF de costos", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static PdfPTable buildCostosTable(List<ReporteCostoOrdenDTO> data) {
        PdfPTable table = new PdfPTable(COLS_COSTOS);
        table.setWidthPercentage(100);
        table.setWidths(COSTO_WIDTHS);

        addHeaderCell(table, "Orden");
        addHeaderCell(table, "Producto");
        addHeaderCell(table, "Material");
        addHeaderCell(table, "Mano Obra");
        addHeaderCell(table, "Indirectos");
        addHeaderCell(table, "Total");

        for (var c : data) {
            table.addCell(toSafeString(c.ordenId()));
            table.addCell(toSafeString(c.producto()));
            table.addCell(toSafeString(c.costoMaterial()));
            table.addCell(toSafeString(c.costoManoObra()));
            table.addCell(toSafeString(c.costoIndirecto()));
            table.addCell(toSafeString(c.total()));
        }

        return table;
    }

    // ===============================
    //  HELPERS
    // ===============================

    private static void addHeaderCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(5);
        table.addCell(cell);
    }

    private static String toSafeString(Object o) {
        return o != null ? o.toString() : "";
    }
}
