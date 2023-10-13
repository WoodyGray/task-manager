package com.woody.task_manager.entity;

import jakarta.persistence.*;

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

    @Column(name = "personalTasks")
    private String linePersonalTasks;

    @Column(name = "publicTasks")
    private String linePublicTasks;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getLinePersonalTasks() {
        return linePersonalTasks;
    }

    public void setLinePersonalTasks(String linePersonalTasks) {
        this.linePersonalTasks = linePersonalTasks;
    }

    public String getLinePublicTasks() {
        return linePublicTasks;
    }

    public void setLinePublicTasks(String linePublicTasks) {
        this.linePublicTasks = linePublicTasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", linePersonalTasks='" + linePersonalTasks + '\'' +
                ", linePublicTasks='" + linePublicTasks + '\'' +
                '}';
    }
}
