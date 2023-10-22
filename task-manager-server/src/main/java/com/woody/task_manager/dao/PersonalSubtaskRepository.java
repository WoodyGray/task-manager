package com.woody.task_manager.dao;

import com.woody.task_manager.entity.PersonalSubtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonalSubtaskRepository extends CrudRepository<PersonalSubtask, Integer> {
}
