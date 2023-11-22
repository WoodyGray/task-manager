package com.woody.task_manager.repository;

import com.woody.task_manager.entity.PublicTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicTaskRepository extends CrudRepository<PublicTask, Integer> {
    Optional<PublicTask> findById(Integer id);
}
