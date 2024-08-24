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

    @Column(name = "status", length = 50) // Nome da coluna e comprimento máximo
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
