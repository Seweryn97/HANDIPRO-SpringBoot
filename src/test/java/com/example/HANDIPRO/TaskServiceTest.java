package com.example.HANDIPRO;

import com.example.HANDIPRO.Repositories.TaskRepository;
import com.example.HANDIPRO.services.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystemException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

public class TaskServiceTest {

    @Test
    public void storeFilesTest() throws FileSystemException {
        var MockTaskRepository = mock(TaskRepository.class);

        MockMultipartFile video = new MockMultipartFile("video", "video_1.mp4","text/mp4", "1234".getBytes());
        MockMultipartFile csv = new MockMultipartFile("csv","plik_testowy.csv","text/csv","1234".getBytes());

        var toTest = new TaskService();

    }
}
