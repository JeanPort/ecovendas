package com.ppsolution.ecovendas.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(String error, String message, Integer status, String cause, LocalDateTime timestamp) {
}
