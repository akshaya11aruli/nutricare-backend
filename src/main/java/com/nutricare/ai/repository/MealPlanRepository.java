package com.nutricare.ai.repository;

import com.nutricare.ai.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    List<MealPlan> findByUserId(Long userId);
    List<MealPlan> findByUserIdAndPlanDate(Long userId, LocalDate planDate);
    List<MealPlan> findByUserIdAndPlanDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    Optional<MealPlan> findByUserIdAndPlanDateAndMealCategory(Long userId, LocalDate planDate, String mealCategory);
    void deleteByUserIdAndPlanDateAndMealCategory(Long userId, LocalDate planDate, String mealCategory);
}
