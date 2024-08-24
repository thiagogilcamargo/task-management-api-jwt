package com.exemplo.tarefaapp.controller;

import com.exemplo.tarefaapp.exception.CustomException;
import com.exemplo.tarefaapp.model.Task;
import com.exemplo.tarefaapp.model.User;
import com.exemplo.tarefaapp.repository.TaskRepository;
import com.exemplo.tarefaapp.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Operation(summary = "Obter lista de status possíveis para tarefas")
    @GetMapping("/status")
    public List<String> getStatuses() {
        return List.of("Pendente", "Em Andamento", "Concluída");
    }

    @Operation(summary = "Criar uma nova tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    })
    @PostMapping
    public Task createTask(@RequestBody Task task, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        task.setOwner(user);
        return taskRepository.save(task);
    }

    @Operation(summary = "Listar as tarefas do usuário logado")
    @GetMapping
    public List<Task> getTasks(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return taskRepository.findByOwner(user);
    }

    @Operation(summary = "Atualizar uma tarefa existente")
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskUpdates, Authentication authentication) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new CustomException("Tarefa não encontrada."));
        User user = (User) authentication.getPrincipal();
        if (!task.getOwner().equals(user)) {
            throw new CustomException("Acesso negado.");
        }
        task.setTitle(taskUpdates.getTitle());
        task.setDescription(taskUpdates.getDescription());
        task.setDueDate(taskUpdates.getDueDate());
        task.setStatus(taskUpdates.getStatus());
        return taskRepository.save(task);
    }

    @Operation(summary = "Deletar uma tarefa")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id, Authentication authentication) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new CustomException("Tarefa não encontrada."));
        User user = (User) authentication.getPrincipal();
        if (!task.getOwner().equals(user)) {
            throw new CustomException("Acesso negado.");
        }
        taskRepository.delete(task);
    }
}
