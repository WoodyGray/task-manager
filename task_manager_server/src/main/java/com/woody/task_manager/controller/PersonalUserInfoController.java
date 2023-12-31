package com.woody.task_manager.controller;

import com.woody.task_manager.dto.PublicTaskDto;
import com.woody.task_manager.dto.UpdateUserPasswordDto;
import com.woody.task_manager.entity.PublicTask;
import com.woody.task_manager.entity.User;
import com.woody.task_manager.exception.AppError;
import com.woody.task_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personal-info")
public class PersonalUserInfoController {
    @Autowired
    private UserService userService;

    @Autowired
    private NotificationController controller;


    @GetMapping("/public-tasks")
    public ResponseEntity<?> getPublicTasks(@RequestHeader (name = "Authorization") String token){
            return userService.findPublicTaskByToken(token.substring(7));
    }

    @GetMapping("/personal-tasks")
    public ResponseEntity<?> getPersonalTasks(@RequestHeader (name = "Authorization") String token){
        return userService.findPersonalTaskByToken(token.substring(7));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader (name = "Authorization") String token){
        User user = userService.findByToken(token.substring(7));
        if (user == null){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "user is null"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/password")
    public String updatePassword(@RequestHeader (name = "Authorization") String token,
                                 @RequestBody UpdateUserPasswordDto updateUserPasswordDto){
        return userService.updatePasswordByToken(token.substring(7),
                updateUserPasswordDto);
    }

    @PostMapping("/add-public-task")
    public ResponseEntity<?> addPublicTask(@RequestHeader (name = "Authorization") String token,
                                           @RequestBody PublicTaskDto publicTaskDto){
        return userService.addPublicTask(token.substring(7), publicTaskDto);
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
