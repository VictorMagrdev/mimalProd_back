package com.example.minimal_prod_backend.util;

import com.example.minimal_prod_backend.dto.ReporteCostoOrdenDTO;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvGenerator {

    public static ByteArrayInputStream generarCostosOrdenCSV(List<ReporteCostoOrdenDTO> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("Orden,Producto,Material,Mano Obra,Indirectos,Total\n");
        data.forEach(c -> sb.append("%d,%s,%s,%s,%s,%s\n".formatted(
                c.ordenId(), c.producto(),
                c.costoMaterial(), c.costoManoObra(),
                c.costoIndirecto(), c.total()
        )));
        return new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}
