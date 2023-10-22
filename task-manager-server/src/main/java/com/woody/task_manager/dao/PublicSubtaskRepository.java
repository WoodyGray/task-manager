package com.woody.task_manager.dao;

import com.woody.task_manager.entity.PublicSubtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicSubtaskRepository extends CrudRepository<PublicSubtask, Integer> {
}
