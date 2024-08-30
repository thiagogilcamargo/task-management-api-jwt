package com.exemplo.tarefaapp.dto;

import com.exemplo.tarefaapp.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(
        Long id,
        @NotBlank @Size(max = 50) String username,
        @NotBlank @Email @Size(max = 100) String email
) {
    public UserDto(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
