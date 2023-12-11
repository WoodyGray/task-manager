package com.woody.task_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "public_tasks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class PublicTask implements Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @ManyToMany()
    @JoinTable(
            name = "users_and_public_tasks"
            , joinColumns = @JoinColumn(name = "id_public_task")
            , inverseJoinColumns = @JoinColumn(name = "id_user")
    )
//    @JsonIgnore
    private List<User> users;

    @OneToMany(
            fetch = FetchType.LAZY
            , cascade = CascadeType.ALL
            , mappedBy = "publicTask"
    )
//    @JsonIgnore
    private List<PublicSubtask> publicSubtasks;

    @Column(name = "status")
    private int status;


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

}
