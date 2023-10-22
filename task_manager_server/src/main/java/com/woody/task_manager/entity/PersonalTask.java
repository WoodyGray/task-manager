package com.woody.task_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "personal_tasks")
@Data
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


    public void addPersonalSubtaskToPersonalTask(PersonalSubtask personalSubtask){
        if (personalSubtasks == null) personalSubtasks = new ArrayList<>();
        personalSubtasks.add(personalSubtask);
    }

    public void removePersonalSubtaskFromPersonalTask(PersonalSubtask personalSubtask){
        if (personalSubtasks != null && personalSubtasks.contains(personalSubtask)){
            personalSubtasks.remove(personalSubtask);
        }
    }

}
