package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.Repositories.TaskRepository;
import com.example.HANDIPRO.models.Task;
import com.example.HANDIPRO.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.FileSystemException;
import java.util.List;


@RestController
public class TaskController {

    @Autowired
    FileStorageService fileStorageService;

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

    @GetMapping("tasks/{id}")
    void exposeTask(@PathVariable int id){
        fileStorageService.getFile(id);
    }

    @PostMapping("/tasks/{id}")
    ResponseEntity<Task> createTask(@RequestParam("video") MultipartFile video ,@RequestParam("csv") MultipartFile csv,
                                    @PathVariable int id) throws FileSystemException {
        if(patientRegistrationRepository.findById(id).isPresent()){
            Task result = fileStorageService.storeFiles(id,video,csv);
            return ResponseEntity.ok(result);
        }
        else{
           return ResponseEntity.notFound().build();
        }
    }

}