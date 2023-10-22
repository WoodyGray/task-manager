package com.woody.task_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_user"
    )
    @JsonIgnore
    private User user;

    @OneToMany(
            fetch = FetchType.LAZY
            ,cascade = CascadeType.ALL
            , mappedBy = "personalTask")
    private List<PersonalSubtask> personalSubtasks;

    @Column(name = "status")
    private int status;

    public PersonalTask() {
    }

    public PersonalTask(String taskName, Date deadline, User user, int status) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PersonalSubtask> getPersonalSubtasks() {
        return personalSubtasks;
    }

    public void addPersonalSubtaskToPersonalTask(PersonalSubtask personalSubtask){
        if (personalSubtasks == null) personalSubtasks = new ArrayList<>();
        personalSubtasks.add(personalSubtask);
    }

    public void setPersonalSubtasks(List<PersonalSubtask> personalSubtasks) {
        this.personalSubtasks = personalSubtasks;
    }

    public void removePersonalSubtaskFromPersonalTask(PersonalSubtask personalSubtask){
        if (personalSubtasks != null && personalSubtasks.contains(personalSubtask)){
            personalSubtasks.remove(personalSubtask);
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
