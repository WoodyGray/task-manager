package com.woody.task_manager.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
