package com.ppsolution.ecovendas.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CartItemResponse(
        Long id,
        Long productId,
        String productNama,
        BigDecimal price,
        Integer quantity,
        BigDecimal subTotal,
        String imgUrl
) {
}
