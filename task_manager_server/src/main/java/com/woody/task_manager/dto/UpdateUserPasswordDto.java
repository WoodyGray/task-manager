package com.woody.task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserPasswordDto {
    private String newPassword;
    private String confirmNewPassword;
}
