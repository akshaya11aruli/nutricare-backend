package com.nutricare.ai.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "meal_selections")
@IdClass(MealSelectionId.class)
public class MealSelection {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Id
    @Column(name = "log_date", nullable = false)
    private LocalDate logDate;

    @Id
    @Column(nullable = false, length = 30)
    private String category;

    @Column(name = "meal_id", nullable = false, length = 100)
    private String mealId;

    @Column(name = "meal_title", nullable = false, length = 150)
    private String mealTitle;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public MealSelection() {}

    public MealSelection(Long userId, LocalDate logDate, String category, String mealId, String mealTitle) {
        this.userId = userId;
        this.logDate = logDate;
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

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
