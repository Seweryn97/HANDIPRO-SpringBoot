package com.example.HANDIPRO.models.DTO;

import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.models.Task;

import java.util.Set;

public class PatientReadDTO {

    private int id;
    private String name;
    private String surname;
    private Set<Task> taskList;

    public PatientReadDTO(Patient patient){
        id = patient.getId();
        name = patient.getName();
        surname = patient.getSurname();
        taskList = patient.getTasks();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(Set<Task> taskList) {
        this.taskList = taskList;
    }
}
