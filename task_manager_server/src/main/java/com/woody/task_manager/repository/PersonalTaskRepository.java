package com.woody.task_manager.repository;

import com.woody.task_manager.entity.PersonalTask;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonalTaskRepository extends CrudRepository<PersonalTask, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE PersonalTask p SET p.status = :status WHERE p.id = :taskId")
    void updateStatusById(Integer taskId, String status);
}
