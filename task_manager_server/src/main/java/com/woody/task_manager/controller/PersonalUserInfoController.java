package com.woody.task_manager.controller;

import com.woody.task_manager.dto.UpdateUserPasswordDto;
import com.woody.task_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personal-info")
public class PersonalUserInfoController {
    @Autowired
    private UserService userService;


    @GetMapping("/public-tasks")
    public ResponseEntity<?> getPublicTasks(@RequestHeader (name = "Authorization") String token){
            return userService.findPublicTaskByToken(token.substring(7));
    }

    @GetMapping("/personal-tasks")
    public ResponseEntity<?> getPersonalTasks(@RequestHeader (name = "Authorization") String token){
        return userService.findPersonalTaskByToken(token.substring(7));
    }

    @PutMapping("/password")
    public String updatePassword(@RequestHeader (name = "Authorization") String token,
                                 @RequestBody UpdateUserPasswordDto updateUserPasswordDto){
        return userService.updatePasswordByToken(token.substring(7),
                updateUserPasswordDto);
    }

//    @GetMapping("/admin")
//    public String adminData(){
//        return "admin data";
//    }
//
//    @GetMapping("/info")
//    public String userData(Principal principal){
//        return principal.getName();
//    }
}
