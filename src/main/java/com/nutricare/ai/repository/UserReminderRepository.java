package com.nutricare.ai.repository;

import com.nutricare.ai.entity.UserReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReminderRepository extends JpaRepository<UserReminder, Long> {
    Optional<UserReminder> findByUserId(Long userId);
}
