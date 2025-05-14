package com.ppsolution.ecovendas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserLoginRequest(
        @NotNull @Email @Size(min = 4, max = 100) String email,
        @NotNull @Size(min = 4, max = 12) String password
) {
}
