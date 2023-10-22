package ru.flamexander.spring.scurityjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserPasswordDto {
    private String newPassword;
    private String confirmNewPassword;
}
