package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.Repositories.TaskRepository;
import com.example.HANDIPRO.models.DTO.TaskReadDTO;
import com.example.HANDIPRO.models.Task;
import com.example.HANDIPRO.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.*;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.util.List;


@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    private final TaskRepository taskRepository;
    private final PatientRegistrationRepository patientRegistrationRepository;

    public TaskController(TaskRepository taskRepository, PatientRegistrationRepository patientRegistrationRepository){
        this.taskRepository = taskRepository;
        this.patientRegistrationRepository = patientRegistrationRepository;
    }

    @GetMapping("/tasks")
    ResponseEntity<List<TaskReadDTO>> exposeAllTasks(){
        return ResponseEntity.ok(taskService.readAllTasks(taskRepository.findAll()));
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<TaskReadDTO> exposeTask(@PathVariable int id){
        return ResponseEntity.ok(new TaskReadDTO(taskService.getTask(id)));
    }

    @GetMapping("/download/{id}")
    ResponseEntity<?> downloadFiles(@PathVariable int id) throws IOException {
        byte[] video = taskRepository.findById(id).orElseThrow(FileNotFoundException::new).getVideodata();
        byte [] csv = taskRepository.findById(id).orElseThrow(FileNotFoundException::new).getCsvdata();
        FileOutputStream videoOutput = new FileOutputStream("C:/Users/Sewuś/Desktop/zapis/video.mp4");
        FileOutputStream csvOutput = new FileOutputStream("C:/Users/Sewuś/Desktop/zapis/plik.csv");
        csvOutput.write(csv);
        videoOutput.write(video);
        csvOutput.close();
        videoOutput.close();

        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/tasks/{id}")
    ResponseEntity<Task> createTask(@RequestParam("video") MultipartFile video ,@RequestParam("csv") MultipartFile csv,
                                    @PathVariable int id) throws FileSystemException {
        if(patientRegistrationRepository.findById(id).isPresent()){
            Task result = taskService.storeFiles(id,video,csv);
            return ResponseEntity.ok(result);
        }
        else{
           return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/tasks/{id}")
    ResponseEntity<TaskReadDTO> updateTask(@RequestParam("video") MultipartFile video ,
                                           @RequestParam("csv") MultipartFile csv, @PathVariable int id) throws IOException {
        return ResponseEntity.ok(new TaskReadDTO(taskService.updateTask(video,csv,id)));
    }

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<String> removeTask(@PathVariable int id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

}