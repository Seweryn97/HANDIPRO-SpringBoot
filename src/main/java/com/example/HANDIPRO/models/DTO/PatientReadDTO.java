package com.example.HANDIPRO.models.DTO;

import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.models.Task;

import java.util.Set;
import java.util.stream.Collectors;

public class PatientReadDTO {

    private int id;
    private String name;
    private String surname;
    private String email;
    private Set<TaskReadDTO> taskList;

    public PatientReadDTO(Patient patient){
        this.id = patient.getId();
        this.name = patient.getName();
        this.surname = patient.getSurname();
        this.email = patient.getEmail();
        this.taskList = patient.getTasks().stream().map(TaskReadDTO::new).collect(Collectors.toSet());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<TaskReadDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(Set<TaskReadDTO> taskList) {
        this.taskList = taskList;
    }
}
