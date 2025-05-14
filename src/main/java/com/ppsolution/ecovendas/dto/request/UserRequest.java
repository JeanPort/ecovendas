package com.ppsolution.ecovendas.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ppsolution.ecovendas.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserRequest(
        @NotNull @Size(min = 4, max = 100) String name,
        @NotNull @Email @Size(min = 5, max = 100) String email,
        @NotNull @Size(min = 4, max = 12) String password,
        @NotNull @Size(min = 4, max = 12) String passwordConfirmation,
        @NotNull @Size(min = 5, max = 12) String phone,
        Role role
) {
}
