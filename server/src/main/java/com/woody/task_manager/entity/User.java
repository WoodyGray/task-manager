package com.woody.task_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login")
    private String login;

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

    public User() {
    }

    public User(String login, String password, String fullName, String email) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PersonalTask> getPersonalTasks() {
        if (personalTasks == null){
            personalTasks = new ArrayList<>();
        }
        return personalTasks;
    }

    public void setPersonalTasks(List<PersonalTask> personalTasks) {
        this.personalTasks = personalTasks;
    }
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

    public List<PublicTask> getPublicTasks() {
        if (publicTasks == null) publicTasks = new ArrayList<>();
        return publicTasks;
    }

    public void setPublicTasks(List<PublicTask> publicTasks) {
        this.publicTasks = publicTasks;
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

//    public List<PublicSubtask> getPublicSubtasks() {
//        if (publicSubtasks == null) publicSubtasks = new ArrayList<>();
//        return publicSubtasks;
//    }
//
//    public void setPublicSubtasks(List<PublicSubtask> publicSubtasks) {
//        this.publicSubtasks = publicSubtasks;
//    }
//
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
