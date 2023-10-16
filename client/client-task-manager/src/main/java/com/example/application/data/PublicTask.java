package com.example.application.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicTask implements Task{

    private int id;

    private String taskName;

    private String description;

    private Date deadline;

    private List<User> users;

    private List<PublicSubtask> publicSubtasks;

    private int status;

    public PublicTask() {
    }

    public PublicTask(String taskName, Date deadline, int status) {
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

    public List<User> getUsers() {
        if (users == null) users = new ArrayList<>();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUserToPublicTask(User user){
        if (this.users == null){
            this.users = new ArrayList<>();
        }
        this.users.add(user);
    }

    public void removeUserFromPublicTask(User user){
        if (this.users != null && this.users.contains(user)){
            this.users.remove(user);
        }
    }

    public List<PublicSubtask> getPublicSubtasks() {
        return publicSubtasks;
    }

    public void setPublicSubtasks(List<PublicSubtask> publicSubtasks) {
        this.publicSubtasks = publicSubtasks;
    }

    public void addPublicSubtaskToPublicTask(PublicSubtask publicSubtask){
        if (publicSubtasks == null){
            publicSubtasks = new ArrayList<>();
        }
        publicSubtasks.add(publicSubtask);
    }

    public void removePublicSubtaskFromPublicTask(PublicSubtask publicSubtask){
        if (publicSubtasks != null && publicSubtasks.contains(publicSubtask)){
            publicSubtasks.remove(publicSubtask);
        }
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PublicTask{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                '}';
    }
}
