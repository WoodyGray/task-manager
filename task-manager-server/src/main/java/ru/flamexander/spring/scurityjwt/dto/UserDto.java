package ru.flamexander.spring.scurityjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.flamexander.spring.scurityjwt.entity.Role;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;

}
