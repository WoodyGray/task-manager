package com.woody.task_manager.dao;

import com.woody.task_manager.entity.PersonalSubtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalSubtaskRepository extends JpaRepository<PersonalSubtask, Integer> {
}
