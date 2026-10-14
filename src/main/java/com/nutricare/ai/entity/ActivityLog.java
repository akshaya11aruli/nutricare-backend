package com.nutricare.ai.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
@IdClass(ActivityLogId.class)
public class ActivityLog {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Id
    @Column(name = "log_date", nullable = false)
    private LocalDate logDate;

    @Column(name = "steps", nullable = false)
    private Integer steps = 0;

    @Column(name = "step_target", nullable = false)
    private Integer stepTarget = 5000;

    @Column(name = "exercise_minutes", nullable = false)
    private Integer exerciseMinutes = 0;

    @Column(name = "calories_burned", nullable = false)
    private Integer caloriesBurned = 0;

    @Column(name = "routine_checks", columnDefinition = "TEXT")
    private String routineChecksJson;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public ActivityLog() {}

    public ActivityLog(Long userId, LocalDate logDate) {
        this.userId = userId;
        this.logDate = logDate;
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
