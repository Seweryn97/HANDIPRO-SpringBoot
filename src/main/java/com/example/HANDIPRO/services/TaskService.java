package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.Repositories.TaskRepository;
import com.example.HANDIPRO.exceptions.FileStorageException;
import com.example.HANDIPRO.exceptions.IllegalFilePathException;
import com.example.HANDIPRO.exceptions.RecordNotFoundException;
import com.example.HANDIPRO.models.DTO.TaskReadDTO;
import com.example.HANDIPRO.models.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    private final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    PatientRegistrationRepository patientRegistrationRepository;

    public List<TaskReadDTO> readAllTasks(List<Task> tasks){

        List<TaskReadDTO> tasksList = new ArrayList<>();

        Iterator<Task> iterator = tasks.iterator();
        iterator.forEachRemaining(task -> {
            tasksList.add(new TaskReadDTO(task));
        });

        return tasksList;
    }

    public Task storeFiles(int id, MultipartFile video, MultipartFile csv) throws FileStorageException {

        String videoFileName = StringUtils.cleanPath(video.getOriginalFilename());
        String csvFileName = StringUtils.cleanPath(csv.getOriginalFilename());

        try{

            if (/*videoFileName.contains("..") ||*/ !Objects.equals(video.getContentType(), "video/mp4")) {
                throw new IllegalFilePathException(videoFileName);
            }
            if (/*csvFileName.contains("..") ||*/ !Objects.equals(csv.getContentType(), "text/csv")) {
                throw new IllegalFilePathException(csvFileName);
            }

        } catch (IllegalFilePathException ex){
            ex.getMessage();
        }

        try {

            logger.warn(video.getContentType());
            logger.warn(csv.getContentType());
            Task task = new Task(videoFileName,csvFileName, video.getBytes(),csv.getBytes());
            task.setPatient(patientRegistrationRepository.findById(id).orElseThrow(() ->
                    new RecordNotFoundException("Patient")));

            return taskRepository.save(task);
        }
        catch (Exception ex){
            throw new FileStorageException();
        }

    }

    public Task getTask(int id) throws RecordNotFoundException{
        return taskRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Task with id "+id));
    }


    public Task updateTask(MultipartFile video, MultipartFile csv, int id) throws IOException, RecordNotFoundException {
        Task toUpdate = getTask(id);

        toUpdate.setVideofilename(video.getOriginalFilename());
        toUpdate.setCsvfilename(csv.getOriginalFilename());
        toUpdate.setVideodata(video.getBytes());
        toUpdate.setCsvdata(csv.getBytes());

        taskRepository.save(toUpdate);

        return toUpdate;
    }

    public String deleteTask(int id) throws RecordNotFoundException {

        taskRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Task"));

        taskRepository.deleteById(id);

        if(taskRepository.findById(id).isPresent()){
            return "Task has not been deleted";
        }
        else return "Task has been deleted ";
    }
}
