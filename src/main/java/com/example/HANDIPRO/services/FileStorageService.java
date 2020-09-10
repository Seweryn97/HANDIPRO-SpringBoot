package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.Repositories.TaskRepository;
import com.example.HANDIPRO.models.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Objects;

@Service
public class FileStorageService {

    private final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    PatientRegistrationRepository patientRegistrationRepository;

    public Task storeFiles(int id, MultipartFile video, MultipartFile csv) throws FileSystemException {

        String videoFileName = StringUtils.cleanPath(video.getOriginalFilename());
        String csvFileName = StringUtils.cleanPath(csv.getOriginalFilename());

        try {
            if (videoFileName.contains("..") || !Objects.equals(video.getContentType(), "video/mp4")) {
                throw new IllegalArgumentException("Inapproprite file path" + videoFileName);
            }

            if (csvFileName.contains("..") || !Objects.equals(csv.getContentType(), "text/csv")) {
                throw new IllegalArgumentException("Inapproprite file path" + csvFileName);
            }
            logger.warn(video.getContentType());
            logger.warn(csv.getContentType());
            Task task = new Task(videoFileName,csvFileName, video.getBytes(),csv.getBytes());
            task.setPatient(patientRegistrationRepository.findById(id).get());

            return taskRepository.save(task);
        } catch (Exception ex) {
            throw  new FileSystemException("Cannot store the file"+ ex);

        }
    }

    public Task getFile(int id){
        return taskRepository.findById(id).orElseThrow(() ->
                new FileSystemNotFoundException("File with id : "+id+" not exists "));

    }
}
