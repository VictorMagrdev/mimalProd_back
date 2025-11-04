package com.example.minimal_prod_backend.util;


import com.example.minimal_prod_backend.dto.IndicadorProductividadDTO;
import com.example.minimal_prod_backend.dto.ReporteCostoOrdenDTO;
import org.openpdf.text.Document;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfGenerator {

    public static ByteArrayInputStream generarProductividadPDF(List<IndicadorProductividadDTO> data) {
        Document doc = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(doc, out);
            doc.open();
            doc.add(new Paragraph("Indicadores de Productividad"));
            doc.add(new Paragraph(" "));

            PdfPTable table = getPdfPTable(data);
            doc.add(table);
            doc.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static PdfPTable getPdfPTable(List<IndicadorProductividadDTO> data) {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.addCell("Orden");
        table.addCell("Producto");
        table.addCell("Unidades");
        table.addCell("Horas");
        table.addCell("Eficiencia (%)");

        for (var p : data) {
            table.addCell(p.ordenId().toString());
            table.addCell(p.producto());
            table.addCell(p.unidadesProducidas().toString());
            table.addCell(p.horasTrabajadas().toString());
            table.addCell(p.eficiencia().toString());
        }
        return table;
    }

    public static ByteArrayInputStream generarCostosOrdenPDF(List<ReporteCostoOrdenDTO> data) {
        Document doc = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(doc, out);
            doc.open();
            doc.add(new Paragraph("Reporte de Costos por Orden"));
            doc.add(new Paragraph(" "));

            PdfPTable table = getPTable(data);
            doc.add(table);
            doc.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static PdfPTable getPTable(List<ReporteCostoOrdenDTO> data) {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.addCell("Orden");
        table.addCell("Producto");
        table.addCell("Material");
        table.addCell("Mano Obra");
        table.addCell("Indirectos");
        table.addCell("Total");

        for (var c : data) {
            table.addCell(c.ordenId().toString());
            table.addCell(c.producto());
            table.addCell(c.costoMaterial().toString());
            table.addCell(c.costoManoObra().toString());
            table.addCell(c.costoIndirecto().toString());
            table.addCell(c.total().toString());
        }
        return table;
    }
}
