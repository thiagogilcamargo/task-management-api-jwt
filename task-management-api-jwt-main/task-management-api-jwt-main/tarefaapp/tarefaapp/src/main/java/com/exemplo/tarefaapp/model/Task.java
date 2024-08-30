package com.exemplo.tarefaapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks") // Nome da tabela no banco de dados
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id") // Nome da coluna da chave primária
    private Long id;

    @Column(name = "title", nullable = false) // Nome da coluna e restrição de não nulo
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private TaskStatus status; // Tipo ajustado para TaskStatus

    @ManyToOne
    @JoinColumn(name = "owner_id") // Nome da coluna para o relacionamento
    private User owner;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() { // Tipo ajustado para TaskStatus
        return status;
    }

    public void setStatus(TaskStatus status) { // Tipo ajustado para TaskStatus
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
