package com.ppsolution.ecovendas.dto.request;

import com.ppsolution.ecovendas.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotNull @Size(min = 5, max = 100) String name,
        @NotNull @Email @Size(min = 5, max = 100) String email,
        @NotNull @Size(min = 5, max = 12) String password,
        @NotNull @Size(min = 5, max = 12) String passwordConfirmation,
        @NotNull @Size(min = 5, max = 12) String phone,
        @NotEmpty @Size(min = 5, max = 20) Role role
) {
}
