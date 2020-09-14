package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.Repositories.TaskRepository;
import com.example.HANDIPRO.models.DTO.TaskReadDTO;
import com.example.HANDIPRO.models.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Quota;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
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

    public Task storeFiles(int id, MultipartFile video, MultipartFile csv) throws FileSystemException {

        String videoFileName = StringUtils.cleanPath(video.getOriginalFilename());
        String csvFileName = StringUtils.cleanPath(csv.getOriginalFilename());

        try {
            if (/*videoFileName.contains("..") ||*/ !Objects.equals(video.getContentType(), "video/mp4")) {
                throw new IllegalArgumentException("Inapproprite file path" + videoFileName);
            }
            if (/*csvFileName.contains("..") ||*/ !Objects.equals(csv.getContentType(), "text/csv")) {
                throw new IllegalArgumentException("Inapproprite file path" + csvFileName);
            }

            logger.warn(video.getContentType());
            logger.warn(csv.getContentType());
            Task task = new Task(videoFileName,csvFileName, video.getBytes(),csv.getBytes());
            task.setPatient(patientRegistrationRepository.findById(id).orElseThrow(() ->
                    new NullPointerException("Patient not exists")));

            return taskRepository.save(task);

        } catch (Exception ex) {
            throw  new FileSystemException("Cannot store the file"+ ex);
        }
    }

    public Task getTask(int id){
        return taskRepository.findById(id).orElseThrow(() ->
                new FileSystemNotFoundException("File with id : "+id+" not exists "));
    }


    public Task updateTask(MultipartFile video, MultipartFile csv, int id) throws IOException {
        Task toUpdate = getTask(id);

        toUpdate.setVideofilename(video.getOriginalFilename());
        toUpdate.setCsvfilename(csv.getOriginalFilename());
        toUpdate.setVideodata(video.getBytes());
        toUpdate.setCsvdata(csv.getBytes());

        taskRepository.save(toUpdate);

        return toUpdate;
    }

    public String deleteTask(int id){
        taskRepository.deleteById(id);

        if(taskRepository.findById(id).isPresent()){
            return "Task has not been deleted";
        }
        else return "Task has been deleted ";
    }
}
