package ru.flamexander.spring.scurityjwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "public_subtasks")
@Data
public class PublicSubtask implements Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id")
    private int id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private Date deadline;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_and_public_subtasks"
            , joinColumns = @JoinColumn(name = "id_public_subtask")
            , inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_public_task")
    @JsonIgnore
    private PublicTask publicTask;

    @Column(name = "status")
    private int status;


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

}
