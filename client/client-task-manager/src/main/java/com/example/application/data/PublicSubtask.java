package com.example.application.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class PublicSubtask implements Task{

    private int id;
    private String taskName;
    private String description;
    private Date deadline;
    private int status;

    private List<User> users;

    private PublicTask publicTask;


    public PublicSubtask() {
    }

    public PublicSubtask(String taskName, Date deadline, int status) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.status = status;
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
