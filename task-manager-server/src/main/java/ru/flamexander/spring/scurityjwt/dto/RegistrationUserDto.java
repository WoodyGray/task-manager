package ru.flamexander.spring.scurityjwt.dto;

import lombok.Data;
import ru.flamexander.spring.scurityjwt.entity.Role;

import javax.persistence.*;
import java.util.Collection;

@Data
public class RegistrationUserDto {

    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String email;

}