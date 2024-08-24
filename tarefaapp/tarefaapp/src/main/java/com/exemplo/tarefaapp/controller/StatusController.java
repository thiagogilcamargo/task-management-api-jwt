package com.exemplo.tarefaapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class StatusController {

    @GetMapping("/status")
    public ResponseEntity<List<String>> getStatuses() {
        List<String> statuses = List.of("Pendente", "Em Andamento", "Concluída");
        return ResponseEntity.ok(statuses);
    }
}
