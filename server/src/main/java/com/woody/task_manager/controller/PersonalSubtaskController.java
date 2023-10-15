package com.woody.task_manager.controller;


import com.woody.task_manager.dao.PersonalSubtaskRepository;
import com.woody.task_manager.dao.PersonalTaskRepository;
import com.woody.task_manager.entity.PersonalSubtask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personal-subtasks")
public class PersonalSubtaskController {

    @Autowired
    private PersonalSubtaskRepository personalSubtaskRepository;

    @GetMapping
    public List<PersonalSubtask> getAllPersonalSubtasks(){
        return personalSubtaskRepository.findAll();
    }

    @GetMapping("/{id}")
    public PersonalSubtask getPersonalSubtask(@PathVariable int id){
        Optional<PersonalSubtask> optional = personalSubtaskRepository.findById(id);
        PersonalSubtask personalSubtask = null;
        if (optional.isPresent()){
            personalSubtask = optional.get();
        }
        return personalSubtask;
    }

    @PostMapping
    public PersonalSubtask addNewPersonalSubtask(@RequestBody PersonalSubtask personalSubtask){
        personalSubtaskRepository.save(personalSubtask);
        return personalSubtask;
    }

    @PutMapping
    public PersonalSubtask updatePersonalSubtask(@RequestBody PersonalSubtask personalSubtask){
        personalSubtaskRepository.save(personalSubtask);
        return personalSubtask;
    }

    @DeleteMapping("/{id}")
    public String deletePersonalSubtask(@PathVariable int id){
        Optional<PersonalSubtask> optional = personalSubtaskRepository.findById(id);
        if (optional.isPresent()){
            personalSubtaskRepository.deleteById(id);
            return "delete success";
        }else {
            return "no such personal subtask with id = " + id;
        }
    }
}
