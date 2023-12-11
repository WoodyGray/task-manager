package com.example.application.services.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PublicTaskDto {
    private String taskName;
    private String description;
    private String deadline;
}
