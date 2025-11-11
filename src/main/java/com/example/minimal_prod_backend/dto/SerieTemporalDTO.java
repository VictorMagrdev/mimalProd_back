package com.example.minimal_prod_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SerieTemporalDTO(
        LocalDate fecha,
        BigDecimal valor
) {}
