package com.woody.task_manager.dao;

import com.woody.task_manager.entity.PersonalTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTaskRepository extends CrudRepository<PersonalTask, Integer> {
}
