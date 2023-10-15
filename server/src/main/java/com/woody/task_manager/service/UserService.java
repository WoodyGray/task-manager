package com.woody.task_manager.service;

import com.woody.task_manager.dao.UserRepository;
import com.woody.task_manager.entity.PersonalTask;
import com.woody.task_manager.entity.PublicTask;
import com.woody.task_manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(int id){
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()){
            user = optional.get();
        }
        return user;
    }

    public User save(User user){
        userRepository.save(user);
        return user;
    }

    public String deleteById(int id){
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()){
            return "such user with id=" + id +" does not found";
        }else {
            User user = optional.get();
            userRepository.delete(user);
            return "delete success";
        }
    }

    public List<PersonalTask> findAllPersonalTasksById(int id){
        Optional<User> optional = userRepository.findById(id);
        List<PersonalTask> personalTasks = null;

        if (optional.isPresent()){
            personalTasks = optional.get().getPersonalTasks();
        }

        return personalTasks;
    }

    public List<PublicTask> findAllPublicTasksById(int id){
        Optional<User> optional = userRepository.findById(id);
        List<PublicTask> publicTasks = null;

        if (optional.isPresent()){
            publicTasks = optional.get().getPublicTasks();
        }

        return publicTasks;
    }

    public List<PersonalTask> findAllPersonalTasks(User user){
        return user.getPersonalTasks();
    }
}
