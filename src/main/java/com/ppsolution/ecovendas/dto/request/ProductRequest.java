package com.ppsolution.ecovendas.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ProductRequest(
        @NotNull @Size(min = 3, max = 100) String name,
        @NotNull @Size(min = 3, max = 255) String description,
        @Positive @NotNull BigDecimal price,
        @NotNull @Positive Integer stockQuantity,
        @NotNull Long idCategory
) {
}
