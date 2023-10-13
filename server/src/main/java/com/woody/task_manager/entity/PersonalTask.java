package com.woody.task_manager.entity;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "personal_tasks")
public class PersonalTask implements Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "id_user")
    private int idUser;

    @Column(name = "id_subtasks")
    private String lineIdSubtasks;

    public PersonalTask() {
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLineIdSubtasks() {
        return lineIdSubtasks;
    }

    public void setLineIdSubtasks(String lineIdSubtasks) {
        this.lineIdSubtasks = lineIdSubtasks;
    }

    @Override
    public String toString() {
        return "PersonalTask{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", idUser=" + idUser +
                ", lineIdSubtasks='" + lineIdSubtasks + '\'' +
                '}';
    }
}
