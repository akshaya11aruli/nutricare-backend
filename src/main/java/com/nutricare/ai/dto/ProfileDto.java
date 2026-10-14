package com.nutricare.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class ProfileDto {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("wellness_goal")
    private String wellnessGoal;

    @JsonProperty("dietary_preference")
    private String dietaryPreference;

    @JsonProperty("allergies")
    private String allergies;

    @JsonProperty("activity_level")
    private String activityLevel;

    @JsonProperty("meal_timings")
    private String mealTimings;

    @JsonProperty("daily_budget")
    private BigDecimal dailyBudget;

    @JsonProperty("preferred_units")
    private String preferredUnits;

    @JsonProperty("liked_foods")
    private String likedFoods;

    @JsonProperty("disliked_foods")
    private String dislikedFoods;

    @JsonProperty("other_restrictions")
    private String otherRestrictions;

    @JsonProperty("additional_notes")
    private String additionalNotes;

    public ProfileDto() {}

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
}
