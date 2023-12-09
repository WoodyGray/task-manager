package com.woody.task_manager.repository;

import com.woody.task_manager.entity.PublicTask;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicTaskRepository extends CrudRepository<PublicTask, Integer> {
    Optional<PublicTask> findById(Integer id);
    Optional<PublicTask> findAllByStatus(Integer status);

    @Transactional
    @Modifying
    @Query("UPDATE PublicTask p SET p.status = :status WHERE p.id = :taskId")
    void updateStatusById(Integer taskId, String status);

    @Transactional
    List<PublicTask> findAllByStatusIsNotLike(Integer status);
}
