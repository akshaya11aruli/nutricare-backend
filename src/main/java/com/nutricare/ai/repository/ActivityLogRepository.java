package com.nutricare.ai.repository;

import com.nutricare.ai.entity.ActivityLog;
import com.nutricare.ai.entity.ActivityLogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, ActivityLogId> {
    Optional<ActivityLog> findByUserIdAndLogDate(Long userId, LocalDate logDate);
    List<ActivityLog> findByUserIdOrderByLogDateDesc(Long userId);
    List<ActivityLog> findByUserIdAndLogDateBetweenOrderByLogDateAsc(Long userId, LocalDate startDate, LocalDate endDate);
}
