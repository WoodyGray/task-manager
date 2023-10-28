package com.example.application.services.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SignUpDto {
    @NotBlank
    private String fullName;
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @Size(min = 8, max = 64, message = "Password must be 8-64 char long")
    private String password;
    @Size(min = 8, max = 64, message = "Password must be 8-64 char long")
    private String confirmPassword;
}
