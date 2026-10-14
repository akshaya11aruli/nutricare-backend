package com.nutricare.ai.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wellness_profiles")
public class WellnessProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "display_name", length = 100)
    private String displayName;

    @Column(name = "wellness_goal", length = 100)
    private String wellnessGoal;

    @Column(name = "dietary_preference", length = 50)
    private String dietaryPreference;

    @Column(columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "activity_level", length = 50)
    private String activityLevel;

    @Column(name = "meal_timings", length = 255)
    private String mealTimings;

    @Column(name = "daily_budget", precision = 10, scale = 2)
    private BigDecimal dailyBudget;

    @Column(name = "preferred_units", length = 50)
    private String preferredUnits;

    @Column(name = "liked_foods", columnDefinition = "TEXT")
    private String likedFoods;

    @Column(name = "disliked_foods", columnDefinition = "TEXT")
    private String dislikedFoods;

    @Column(name = "other_restrictions", columnDefinition = "TEXT")
    private String otherRestrictions;

    @Column(name = "additional_notes", columnDefinition = "TEXT")
    private String additionalNotes;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public WellnessProfile() {}

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getWellnessGoal() {
        return wellnessGoal;
    }

    public void setWellnessGoal(String wellnessGoal) {
        this.wellnessGoal = wellnessGoal;
    }

    public String getDietaryPreference() {
        return dietaryPreference;
    }

    public void setDietaryPreference(String dietaryPreference) {
        this.dietaryPreference = dietaryPreference;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getMealTimings() {
        return mealTimings;
    }

    public void setMealTimings(String mealTimings) {
        this.mealTimings = mealTimings;
    }

    public BigDecimal getDailyBudget() {
        return dailyBudget;
    }

    public void setDailyBudget(BigDecimal dailyBudget) {
        this.dailyBudget = dailyBudget;
    }

    public String getPreferredUnits() {
        return preferredUnits;
    }

    public void setPreferredUnits(String preferredUnits) {
        this.preferredUnits = preferredUnits;
    }

    public String getLikedFoods() {
        return likedFoods;
    }

    public void setLikedFoods(String likedFoods) {
        this.likedFoods = likedFoods;
    }

    public String getDislikedFoods() {
        return dislikedFoods;
    }

    public void setDislikedFoods(String dislikedFoods) {
        this.dislikedFoods = dislikedFoods;
    }

    public String getOtherRestrictions() {
        return otherRestrictions;
    }

    public void setOtherRestrictions(String otherRestrictions) {
        this.otherRestrictions = otherRestrictions;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
