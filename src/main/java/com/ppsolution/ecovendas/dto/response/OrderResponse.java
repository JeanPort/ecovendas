package com.ppsolution.ecovendas.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ppsolution.ecovendas.model.enums.OrderStatus;
import com.ppsolution.ecovendas.model.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OrderResponse(
        Long id,
        Long userId,
        OrderStatus status,
        List<OrderItemResponse> items,
        AddressResponse shippingAddress,
        PaymentMethod paymentMethod,
        BigDecimal totalAmount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
