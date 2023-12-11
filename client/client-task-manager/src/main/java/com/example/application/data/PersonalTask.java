package com.example.application.data;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PersonalTask implements Task {

    private int id;
    private String taskName;
    private String description;
    private LocalDateTime deadline;
    private int status;

    private User user;

    private List<PersonalSubtask> personalSubtasks;

    public PersonalTask() {
    }

    public PersonalTask(String taskName, LocalDateTime deadline, User user, int status) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.user = user;
        this.status = status;
    }


    public void addPersonalSubtaskToPersonalTask(PersonalSubtask personalSubtask){
        if (personalSubtasks == null) personalSubtasks = new ArrayList<>();
        personalSubtasks.add(personalSubtask);
    }

    public void removePersonalSubtaskFromPersonalTask(PersonalSubtask personalSubtask){
        if (personalSubtasks != null && personalSubtasks.contains(personalSubtask)){
            personalSubtasks.remove(personalSubtask);
        }
    }

    @Override
    public String toString() {
        return "PersonalTask{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", user=" + user +
                ", personalSubtasks=" + personalSubtasks +
                ", status=" + status +
                '}';
    }
}
