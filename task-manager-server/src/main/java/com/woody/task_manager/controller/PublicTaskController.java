package com.woody.task_manager.controller;

import com.woody.task_manager.dao.PublicTaskRepository;
import com.woody.task_manager.entity.PublicTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public-tasks")
public class PublicTaskController {

    @Autowired
    private PublicTaskRepository publicTaskRepository;

    @GetMapping
    public List<PublicTask> getAllPublicTasks(){
        return publicTaskRepository.findAll();
    }

    @GetMapping("/{id}")
    public PublicTask getPublicTask(@PathVariable int id){
        Optional<PublicTask> optional = publicTaskRepository.findById(id);
        PublicTask publicTask = null;
        if (optional.isPresent()){
            publicTask = optional.get();
        }
        return publicTask;
    }

    @PostMapping
    public PublicTask addNewPublicTask(@RequestBody PublicTask publicTask){
        publicTaskRepository.save(publicTask);
        return publicTask;
    }

    @PutMapping
    public PublicTask updatePublicTask(@RequestBody PublicTask publicTask){
        publicTaskRepository.save(publicTask);
        return publicTask;
    }

    @DeleteMapping("/{id}")
    public String deletePublicTask(@PathVariable int id){
        Optional<PublicTask> optional = publicTaskRepository.findById(id);
        if (optional.isPresent()){
            publicTaskRepository.deleteById(id);
            return "delete public task with id = " + id +" success";
        }else {
            return "no such public task with id =" + id;
        }
    }
}
