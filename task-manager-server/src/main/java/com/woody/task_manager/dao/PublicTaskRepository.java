package com.woody.task_manager.dao;

import com.woody.task_manager.entity.PublicTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicTaskRepository extends CrudRepository<PublicTask, Integer> {
}
