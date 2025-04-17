package com.ppsolution.ecovendas.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CategoryRequest(
        @NotNull @Size(min = 3, max = 100) String name,
        @NotNull @Size(min = 3, max = 255)String description
) {
}
