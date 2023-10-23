package com.example.application.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogInDto {
    private String username;
    private String password;
}
