package com.nutricare.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class ActivityDto {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("log_date")
    private LocalDate logDate;

    private Integer steps;

    @JsonProperty("step_target")
    private Integer stepTarget;

    @JsonProperty("exercise_minutes")
    private Integer exerciseMinutes;

    @JsonProperty("calories_burned")
    private Integer caloriesBurned;

    @JsonProperty("routine_checks")
    private String routineChecksJson;

    public ActivityDto() {}

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

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getStepTarget() {
        return stepTarget;
    }

    public void setStepTarget(Integer stepTarget) {
        this.stepTarget = stepTarget;
    }

    public Integer getExerciseMinutes() {
        return exerciseMinutes;
    }

    public void setExerciseMinutes(Integer exerciseMinutes) {
        this.exerciseMinutes = exerciseMinutes;
    }

    public Integer getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(Integer caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public String getRoutineChecksJson() {
        return routineChecksJson;
    }

    public void setRoutineChecksJson(String routineChecksJson) {
        this.routineChecksJson = routineChecksJson;
    }
}
