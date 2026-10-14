package com.nutricare.ai.service;

import com.nutricare.ai.dto.MealPlanDto;
import com.nutricare.ai.entity.MealPlan;
import com.nutricare.ai.repository.MealPlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;

    public MealPlanService(MealPlanRepository mealPlanRepository) {
        this.mealPlanRepository = mealPlanRepository;
    }

    @Transactional
    public MealPlanDto saveOrUpdatePlan(MealPlanDto dto) {
        if (dto.getUserId() == null || dto.getPlanDate() == null || dto.getMealCategory() == null) {
            throw new IllegalArgumentException("User ID, plan date, and meal category are required");
        }

        Optional<MealPlan> existing = mealPlanRepository.findByUserIdAndPlanDateAndMealCategory(
                dto.getUserId(),
                dto.getPlanDate(),
                dto.getMealCategory()
        );

        MealPlan plan;
        if (existing.isPresent()) {
            plan = existing.get();
            plan.setMealId(dto.getMealId() != null ? dto.getMealId().trim() : "");
            plan.setMealName(dto.getMealName() != null ? dto.getMealName().trim() : "");
        } else {
            plan = new MealPlan(
                    dto.getUserId(),
                    dto.getPlanDate(),
                    dto.getMealCategory(),
                    dto.getMealId() != null ? dto.getMealId().trim() : "",
                    dto.getMealName() != null ? dto.getMealName().trim() : ""
            );
        }

        MealPlan saved = mealPlanRepository.save(plan);
        return toDto(saved);
    }

    @Transactional
    public List<MealPlanDto> batchSavePlans(Long userId, List<MealPlanDto> dtoList) {
        if (userId == null || dtoList == null) return List.of();
        return dtoList.stream()
                .map(dto -> {
                    dto.setUserId(userId);
                    return saveOrUpdatePlan(dto);
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MealPlanDto> getPlans(Long userId, LocalDate startDate, LocalDate endDate) {
        List<MealPlan> plans;
        if (startDate != null && endDate != null) {
            plans = mealPlanRepository.findByUserIdAndPlanDateBetween(userId, startDate, endDate);
        } else {
            plans = mealPlanRepository.findByUserId(userId);
        }
        return plans.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void deletePlan(Long userId, LocalDate date, String category) {
        mealPlanRepository.deleteByUserIdAndPlanDateAndMealCategory(userId, date, category);
    }

    private MealPlanDto toDto(MealPlan entity) {
        return new MealPlanDto(
                entity.getId(),
                entity.getUserId(),
                entity.getPlanDate(),
                entity.getMealCategory(),
                entity.getMealId(),
                entity.getMealName()
        );
    }
}
