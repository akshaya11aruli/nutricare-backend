package com.nutricare.ai.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_reminders")
public class UserReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private Boolean enabled = false;

    @Column(length = 10)
    private String breakfast = "08:00";

    @Column(length = 10)
    private String lunch = "13:00";

    @Column(length = 10)
    private String snack = "16:00";

    @Column(length = 10)
    private String dinner = "20:00";

    @Column(length = 10)
    private String water = "10:30";

    @Column(length = 10)
    private String movement = "18:00";

    @Column(length = 10)
    private String winddown = "22:00";

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public UserReminder() {}

    public UserReminder(Long userId) {
        this.userId = userId;
        this.enabled = false;
        this.breakfast = "08:00";
        this.lunch = "13:00";
        this.snack = "16:00";
        this.dinner = "20:00";
        this.water = "10:30";
        this.movement = "18:00";
        this.winddown = "22:00";
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getSnack() {
        return snack;
    }

    public void setSnack(String snack) {
        this.snack = snack;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getWinddown() {
        return winddown;
    }

    public void setWinddown(String winddown) {
        this.winddown = winddown;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
