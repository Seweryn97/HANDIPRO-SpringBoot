package com.example.HANDIPRO.Repositories;

import com.example.HANDIPRO.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();

    Task save(Task Entity);

    Optional<Task> findById(int id);

    //void delete(Task Entity);

    void deleteById(Integer id);
}
