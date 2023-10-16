package com.example.application.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicSubtask implements Task{

    private int id;

    private String taskName;

    private String description;

    private Date deadline;

    private List<User> users;

    private PublicTask publicTask;

    private int status;

    public PublicSubtask() {
    }

    public PublicSubtask(String taskName, Date deadline, int status) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PublicTask getPublicTask() {
        return publicTask;
    }

    public void setPublicTask(PublicTask publicTask) {
        this.publicTask = publicTask;
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

    public List<User> getUsers() {
        return users;
    }

    public void addUserToPublicSubtask(User user){
        if (this.users == null){
            this.users = new ArrayList<>();
        }
        this.users.add(user);
    }

    public void removeUserFromPublicSubtask(User user){
        if (this.users != null && this.users.contains(user)){
            this.users.remove(user);
        }
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                '}';
    }
}
