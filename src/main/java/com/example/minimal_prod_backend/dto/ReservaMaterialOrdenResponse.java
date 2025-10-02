package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ReservaMaterialOrdenResponse(
        Long id,
        Long ordenId,
        Long productoId,
        Long loteId,
        BigDecimal cantidadReservada,
        Long unidadId,
        OffsetDateTime fechaReserva
) {}