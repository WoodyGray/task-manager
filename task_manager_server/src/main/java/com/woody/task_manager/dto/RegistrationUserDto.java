package com.woody.task_manager.dto;

import lombok.Data;

@Data
public class RegistrationUserDto {

    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String email;

}