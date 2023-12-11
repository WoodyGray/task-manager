package com.woody.task_manager.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PublicTaskDto {
    private String taskName;
    private String description;
    private String deadline;
}
