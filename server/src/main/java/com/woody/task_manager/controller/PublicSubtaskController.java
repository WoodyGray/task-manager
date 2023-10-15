package com.woody.task_manager.controller;

import com.woody.task_manager.dao.PublicSubtaskRepository;
import com.woody.task_manager.dao.PublicTaskRepository;
import com.woody.task_manager.entity.PublicSubtask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public-subtasks")
public class PublicSubtaskController {

    @Autowired
    private PublicSubtaskRepository publicSubtaskRepository;

    @GetMapping
    public List<PublicSubtask> getAllPublicSubtasks(){
        return publicSubtaskRepository.findAll();
    }

    @PostMapping
    public PublicSubtask addNewPublicSubtask(@RequestBody PublicSubtask publicSubtask){
        publicSubtaskRepository.save(publicSubtask);
        return publicSubtask;
    }

    @PutMapping
    public PublicSubtask updatePublicSubtask(@RequestBody PublicSubtask publicSubtask){
        publicSubtaskRepository.save(publicSubtask);
        return publicSubtask;
    }

    @DeleteMapping("/{id}")
    public String deletePublicSubtask(@PathVariable int id){
        Optional<PublicSubtask> optional = publicSubtaskRepository.findById(id);
        if (optional.isPresent()){
            publicSubtaskRepository.deleteById(id);
            return "delete public task with id = " + id + " success";
        }else {
            return "no such public task with id = " + id;
        }
    }
}
