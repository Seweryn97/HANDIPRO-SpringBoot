package com.example.HANDIPRO.adapter;


import com.example.HANDIPRO.Repositories.TaskRepository;
import com.example.HANDIPRO.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> {
}
