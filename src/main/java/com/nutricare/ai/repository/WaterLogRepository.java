package com.nutricare.ai.repository;

import com.nutricare.ai.entity.WaterLog;
import com.nutricare.ai.entity.WaterLogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WaterLogRepository extends JpaRepository<WaterLog, WaterLogId> {
    Optional<WaterLog> findByUserIdAndLogDate(Long userId, LocalDate logDate);
    List<WaterLog> findByUserIdOrderByLogDateDesc(Long userId);
    List<WaterLog> findByUserIdAndLogDateBetweenOrderByLogDateAsc(Long userId, LocalDate startDate, LocalDate endDate);
}
