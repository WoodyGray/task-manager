package com.woody.task_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_and_public_tasks"
            , joinColumns = @JoinColumn(name = "id_user")
            , inverseJoinColumns = @JoinColumn(name = "id_public_task")
    )
    @JsonIgnore
    private List<PublicTask> publicTasks;

//    @ManyToMany
//    @JoinTable(
//            name = "users_and_public_subtasks"
//            , joinColumns = @JoinColumn(name = "id_user")
//            , inverseJoinColumns = @JoinColumn(name = "id_public_subtask")
//    )
//    private List<PublicSubtask> publicSubtasks;

    @OneToMany(fetch = FetchType.LAZY
            ,cascade = CascadeType.ALL
            ,mappedBy = "user")
    @JsonIgnore
    private List<PersonalTask> personalTasks;

    @ManyToMany
    @JoinTable(
            name = "users_and_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Collection<Role> roles;


    public void addPersonalTaskToUser(PersonalTask personalTask){
        if (this.personalTasks == null){
            this.personalTasks = new ArrayList<>();
        }
        this.personalTasks.add(personalTask);
    }
    public void removePersonalTaskFromUser(PersonalTask personalTask){
        if (this.personalTasks != null && this.personalTasks.contains(personalTask)) {
            this.personalTasks.remove(personalTask);
        }
    }

    public void addPublicTaskToUser(PublicTask publicTask){
        if (this.publicTasks == null){
            this.publicTasks = new ArrayList<>();
        }
        this.publicTasks.add(publicTask);
    }
    public void removePublicTaskFromUser(PublicTask publicTask){
        if (this.publicTasks != null && this.publicTasks.contains(publicTask)){
            this.publicTasks.remove(publicTask);
        }
    }


//    public void addPublicSubtaskToUser(PublicSubtask publicSubtask){
//        if (this.publicSubtasks == null){
//            this.publicSubtasks = new ArrayList<>();
//        }
//        this.publicSubtasks.add(publicSubtask);
//    }
//
//    public void removePublicSubtaskFromUser(PublicSubtask publicSubtask){
//        if (this.publicSubtasks != null && this.publicSubtasks.contains(publicSubtask)){
//            this.publicSubtasks.remove(publicSubtask);
//        }
//    }
}
