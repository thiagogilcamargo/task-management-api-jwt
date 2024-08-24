package com.exemplo.tarefaapp.repository;

import com.exemplo.tarefaapp.model.Task;
import com.exemplo.tarefaapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwner(User owner);
}