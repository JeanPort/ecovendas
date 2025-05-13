package com.ppsolution.ecovendas.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ppsolution.ecovendas.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserResponse(
        String name,
        String email,
        String password,
        String phone,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
