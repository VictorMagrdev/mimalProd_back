package com.example.minimal_prod_backend.dto.Request;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ReservaMaterialOrdenRequest(
        Long ordenId,
        Long productoId,
        Long loteId,
        BigDecimal cantidadReservada,
        Long unidadId,
        OffsetDateTime fechaReserva
) {
}