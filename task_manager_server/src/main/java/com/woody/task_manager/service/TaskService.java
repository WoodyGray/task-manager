package com.woody.task_manager.service;

import com.woody.task_manager.entity.PersonalSubtask;
import com.woody.task_manager.entity.PersonalTask;
import com.woody.task_manager.entity.PublicSubtask;
import com.woody.task_manager.entity.PublicTask;
import com.woody.task_manager.repository.PersonalSubtaskRepository;
import com.woody.task_manager.repository.PersonalTaskRepository;
import com.woody.task_manager.repository.PublicSubtaskRepository;
import com.woody.task_manager.repository.PublicTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private PublicTaskRepository publicTaskRepository;
    private PublicSubtaskRepository publicSubtaskRepository;
    private PersonalTaskRepository personalTaskRepository;
    private PersonalSubtaskRepository personalSubtaskRepository;

    @Autowired
    public void setPublicTaskRepository(PublicTaskRepository publicTaskRepository) {
        this.publicTaskRepository = publicTaskRepository;
    }

    @Autowired
    public void setPublicSubtaskRepository(PublicSubtaskRepository publicSubtaskRepository) {
        this.publicSubtaskRepository = publicSubtaskRepository;
    }

    @Autowired
    public void setPersonalTaskRepository(PersonalTaskRepository personalTaskRepository) {
        this.personalTaskRepository = personalTaskRepository;
    }

    @Autowired
    public void setPersonalSubtaskRepository(PersonalSubtaskRepository personalSubtaskRepository) {
        this.personalSubtaskRepository = personalSubtaskRepository;
    }

    public List<PublicTask> getAllPublicTasks(){
        return (List<PublicTask>) publicTaskRepository.findAll();
    }

    public List<PublicSubtask> getAllPublicSubtask(){
        return (List<PublicSubtask>) publicSubtaskRepository.findAll();
    }

    public List<PersonalTask> getAllPersonalTask(){
        return (List<PersonalTask>) personalTaskRepository.findAll();
    }

    public List<PersonalSubtask> getAllPersonalSubtask(){
        return (List<PersonalSubtask>) personalSubtaskRepository.findAll();
    }
    
}
