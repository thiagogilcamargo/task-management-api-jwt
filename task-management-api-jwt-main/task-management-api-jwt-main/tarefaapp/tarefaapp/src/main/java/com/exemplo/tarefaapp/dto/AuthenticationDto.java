package com.exemplo.tarefaapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDto(
        @NotBlank @Email String username,
        @NotBlank String password
) {
}
