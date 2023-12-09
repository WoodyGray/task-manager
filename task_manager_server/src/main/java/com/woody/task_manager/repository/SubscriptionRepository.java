package com.woody.task_manager.repository;

import com.woody.task_manager.entity.SubscriptionEntity;
import com.woody.task_manager.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, String> {

    // Пример кастомного запроса с использованием JPQL
    @Transactional
    @Query("SELECT s FROM SubscriptionEntity s WHERE s.user = :user")
    List<SubscriptionEntity> findAllByUser(User user);
}
