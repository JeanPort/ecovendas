package com.ppsolution.ecovendas.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AddressRequest(
        @NotNull @Size(min = 3, max = 100) String street,
        @NotNull @Size(min = 3, max = 10) String number,
        @NotNull @Size(min = 3, max = 255) String complement,
        @NotNull @Size(min = 3, max = 255) String city,
        @NotNull @Size(min = 2, max = 2) String state,
        @NotNull @Size(min = 3, max = 20) String zipCode,
        Integer isDefault
) {
}
