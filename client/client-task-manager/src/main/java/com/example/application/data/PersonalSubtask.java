package com.example.application.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
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
