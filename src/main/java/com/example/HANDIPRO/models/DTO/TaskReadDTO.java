package com.example.HANDIPRO.models.DTO;

import com.example.HANDIPRO.models.Task;

public class TaskReadDTO {

    private int id;
    private String videofilename;
    private String csvfilename;

    public TaskReadDTO(Task task){
        this.id = task.getId();
        this.videofilename = task.getVideofilename();
        this.csvfilename = task.getCsvfilename();
    }

    public int getId() {
        return id;
    }

    public String getVideofilename() {
        return videofilename;
    }

    public String getCsvfilename() {
        return csvfilename;
    }
}
