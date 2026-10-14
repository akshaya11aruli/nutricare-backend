package com.nutricare.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class MealPlanDto {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("plan_date")
    private LocalDate planDate;

    @JsonProperty("meal_category")
    private String mealCategory;

    @JsonProperty("meal_id")
    private String mealId;

    @JsonProperty("meal_name")
    private String mealName;

    public MealPlanDto() {}

    public MealPlanDto(Long id, Long userId, LocalDate planDate, String mealCategory, String mealId, String mealName) {
        this.id = id;
        this.userId = userId;
        this.planDate = planDate;
        this.mealCategory = mealCategory;
        this.mealId = mealId;
        this.mealName = mealName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDate planDate) {
        this.planDate = planDate;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
