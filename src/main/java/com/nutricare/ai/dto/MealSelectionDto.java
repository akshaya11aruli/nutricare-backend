package com.nutricare.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class MealSelectionDto {

    @JsonProperty("user_id")
    private Long userId;

    private String category;

    @JsonProperty("meal_id")
    private String mealId;

    @JsonProperty("meal_title")
    private String mealTitle;

    @JsonProperty("log_date")
    private LocalDate logDate;

    public MealSelectionDto() {}

    public MealSelectionDto(String category, String mealId, String mealTitle) {
        this.category = category;
        this.mealId = mealId;
        this.mealTitle = mealTitle;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }
}
