package com.woody.task_manager.controller;

import com.woody.task_manager.dao.PersonalTaskRepository;
import com.woody.task_manager.entity.PersonalTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personal-tasks")
public class PersonalTaskController {

    @Autowired
    private PersonalTaskRepository personalTaskRepository;

    @GetMapping
    public List<PersonalTask> getAllPersonalTask(){ return personalTaskRepository.findAll(); }

    @GetMapping("/{id}")
    public PersonalTask getPersonalTask(@PathVariable int id){
        Optional<PersonalTask> optional = personalTaskRepository.findById(id);
        PersonalTask personalTask = null;
        if (optional.isPresent()){
            personalTask = optional.get();
        }
        return personalTask;
    }

    @PostMapping
    public PersonalTask addNewPersonalTask(@RequestBody PersonalTask personalTask){
        personalTaskRepository.save(personalTask);
        return personalTask;
    }

    @PutMapping
    public PersonalTask updatePersonalTask(@RequestBody PersonalTask personalTask){
        personalTaskRepository.save(personalTask);
        return personalTask;
    }

    @DeleteMapping("/{id}")
    public String deletePersonalTask(@PathVariable int id){
        Optional<PersonalTask> optional = personalTaskRepository.findById(id);
        if (optional.isPresent()){
            personalTaskRepository.deleteById(id);
            return "delete success";
        }else {
            return "no such personal task with id = " + id;
        }
    }
}
