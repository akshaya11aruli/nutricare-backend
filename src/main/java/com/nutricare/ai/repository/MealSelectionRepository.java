package com.nutricare.ai.repository;

import com.nutricare.ai.entity.MealSelection;
import com.nutricare.ai.entity.MealSelectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MealSelectionRepository extends JpaRepository<MealSelection, MealSelectionId> {
    List<MealSelection> findByUserIdAndLogDate(Long userId, LocalDate logDate);
    Optional<MealSelection> findByUserIdAndLogDateAndCategory(Long userId, LocalDate logDate, String category);
}
