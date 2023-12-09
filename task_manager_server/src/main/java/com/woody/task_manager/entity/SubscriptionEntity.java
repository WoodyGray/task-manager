package com.woody.task_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
@Data
public class SubscriptionEntity {

    @Id
    @Column(name = "endpoint")
    private String endpoint;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;

    @Column(name = "p256dh")
    private String p256dh;

    @Column(name = "auth")
    private String auth;
}
