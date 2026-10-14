package com.nutricare.ai.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MealSelectionId implements Serializable {
    private Long userId;
    private LocalDate logDate;
    private String category;

    public MealSelectionId() {}

    public MealSelectionId(Long userId, LocalDate logDate, String category) {
        this.userId = userId;
        this.logDate = logDate;
        this.category = category;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealSelectionId that = (MealSelectionId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(logDate, that.logDate) &&
               Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, logDate, category);
    }
}
