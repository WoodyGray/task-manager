package com.example.application.data;

import lombok.Data;

import java.util.Date;


public interface Task {
    public int getId();
    public void setId(int id);
    public String getTaskName();
    public void setTaskName(String taskName);
    public String getDescription();
    public void setDescription(String description);
    public Date getDeadline();
    public void setDeadline(Date date);
    public int getStatus();
    public void setStatus(int status);
}
