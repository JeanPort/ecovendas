package com.ppsolution.ecovendas.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ErrorValidateResponse(
        String error,
        String message,
        Integer status,
        String cause,
        LocalDateTime timestamp,
        Map<String, List<String>> errors
) {
}
