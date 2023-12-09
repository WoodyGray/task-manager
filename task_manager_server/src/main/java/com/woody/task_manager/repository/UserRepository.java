package com.woody.task_manager.repository;


import com.woody.task_manager.entity.SubscriptionEntity;
import com.woody.task_manager.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    Optional<User> findByUsername(String username);
}
