package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.Repositories.TaskRepository;
import com.example.HANDIPRO.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class TaskController {

    private final TaskRepository taskRepository;
    private final PatientRegistrationRepository patientRegistrationRepository;

    public TaskController(TaskRepository taskRepository, PatientRegistrationRepository patientRegistrationRepository){
        this.taskRepository = taskRepository;
        this.patientRegistrationRepository = patientRegistrationRepository;
    }

    @GetMapping("/tasks")
    ResponseEntity<List<Task>> exposeAllTasks(){
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @PostMapping("/tasks/{id}")
    ResponseEntity<Task> createTask(@RequestBody @Valid Task task, @PathVariable int id){
        if(patientRegistrationRepository.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
           Task result = taskRepository.save(task);
           return ResponseEntity.ok(result);
        }
    }

}