package com.woody.task_manager.controller;

import com.woody.task_manager.entity.PersonalTask;
import com.woody.task_manager.entity.PublicTask;
import com.woody.task_manager.entity.User;
import com.woody.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return userService.findById(id);
    }

    @GetMapping("/{id}/personal-tasks")
    public List<PersonalTask> getUserPersonalTasks(@PathVariable int id){
        return userService.findAllPersonalTasksById(id);
    }
    @GetMapping("/personal-tasks")
    public List<PersonalTask> getUserPersonalTasks(@RequestBody User user){
        return getUserPersonalTasks(user.getId());
    }

    @GetMapping("{id}/public-tasks")
    public List<PublicTask> getUserPublicTasks(@PathVariable int id){
        return userService.findAllPublicTasksById(id);
    }

    @GetMapping("/public-tasks")
    public List<PublicTask> getUserPublicTasks(@RequestBody User user){
        return getUserPublicTasks(user.getId());
    }

    @PostMapping()
    public User addNewUser(@RequestBody User user){
        userService.save(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        userService.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteById(id);
    }
}
