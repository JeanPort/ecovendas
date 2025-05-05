package com.ppsolution.ecovendas.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AddressResponse(
        String street,
        String number,
        String complement,
        String city,
        String state,
        String zipCode,
        Integer isDefault,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
