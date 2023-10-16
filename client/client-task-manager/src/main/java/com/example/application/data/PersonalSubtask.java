package com.example.application.data;

import java.util.Date;

public class PersonalSubtask implements Task{

    private int id;

    private String taskName;

    private String description;

    private Date deadline;

    private int status;

    private PersonalTask personalTask;

    public PersonalSubtask() {
    }

    public PersonalSubtask(String taskName, String description, Date deadline, int status) {
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public PersonalTask getPersonalTask() {
        return personalTask;
    }

    public void setPersonalTask(PersonalTask personalTask) {
        this.personalTask = personalTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PersonalSubtasks{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                '}';
    }
}
