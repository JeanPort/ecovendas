package com.ppsolution.ecovendas.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CartResponse(
        Long id,
        List<CartItemResponse> items,
        Integer totalItems,
        BigDecimal totalAmout,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
