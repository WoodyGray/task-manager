package com.woody.task_manager.service;

import com.woody.task_manager.entity.*;
import com.woody.task_manager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private PublicTaskRepository publicTaskRepository;
    private PublicSubtaskRepository publicSubtaskRepository;
    private PersonalTaskRepository personalTaskRepository;
    private PersonalSubtaskRepository personalSubtaskRepository;
//    private SubscriptionRepository subscriptionRepository;
//
//    @Autowired
//    public void setSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
//        this.subscriptionRepository = subscriptionRepository;
//    }

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
    public List<PublicTask> getAllNoExtractPublicTasks(){
        return publicTaskRepository.findAllByStatusIsNotLike(2);
    }

    public List<PublicSubtask> getAllPublicSubtask(){
        return (List<PublicSubtask>) publicSubtaskRepository.findAll();
    }

    public PublicTask getPublicTaskById(Integer id){
        return publicTaskRepository.findById(id).get();
    }

    public List<PersonalTask> getAllPersonalTask(){
        return (List<PersonalTask>) personalTaskRepository.findAll();
    }

    public List<PersonalSubtask> getAllPersonalSubtask(){
        return (List<PersonalSubtask>) personalSubtaskRepository.findAll();
    }

    public void saveTask(PublicTask publicTask){
        publicTaskRepository.save(publicTask);
    }
    public void saveTask(PublicSubtask publicSubtask){
        publicSubtaskRepository.save(publicSubtask);
    }
    public void saveTask(PersonalTask personalTask){
        personalTaskRepository.save(personalTask);
    }
    public void saveTask(PersonalSubtask personalSubtask){
        personalSubtaskRepository.save(personalSubtask);
    }

}
