package com.example.application.data;

import java.time.LocalDateTime;


public interface Task {
    public int getId();
    public void setId(int id);
    public String getTaskName();
    public void setTaskName(String taskName);
    public String getDescription();
    public void setDescription(String description);
    public LocalDateTime getDeadline();
    public void setDeadline(LocalDateTime date);
    public int getStatus();
    public void setStatus(int status);
}
