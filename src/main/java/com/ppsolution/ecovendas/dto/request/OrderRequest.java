package com.ppsolution.ecovendas.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ppsolution.ecovendas.model.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OrderRequest(
        @NotNull Long addressId,
        @NotNull PaymentMethod paymentMethod
) {
}
