package com.example.application.data;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PublicTask implements Task{

    private int id;
    private String taskName;
    private String description;
    private LocalDateTime deadline;
    private int status;

    private List<User> users;

    private List<PublicSubtask> publicSubtasks;

    public PublicTask() {
    }

    public PublicTask(String taskName, LocalDateTime deadline, int status) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.status = status;
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
