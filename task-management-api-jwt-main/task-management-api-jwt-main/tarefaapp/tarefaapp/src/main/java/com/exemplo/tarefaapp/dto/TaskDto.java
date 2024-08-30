package com.exemplo.tarefaapp.dto;

import com.exemplo.tarefaapp.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record TaskDto(
        Long id,
        @NotBlank @Size(max = 100) String title,
        @NotBlank @Size(max = 500) String description,
        LocalDate dueDate,
        String status,
        Long userId
) {
    public TaskDto(Task task) {
        this(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus().name(),
                task.getOwner().getId()
        );
    }
}
