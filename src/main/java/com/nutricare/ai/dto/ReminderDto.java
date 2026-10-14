package com.nutricare.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReminderDto {

    @JsonProperty("user_id")
    private Long userId;

    private Boolean enabled = false;
    private String breakfast = "08:00";
    private String lunch = "13:00";
    private String snack = "16:00";
    private String dinner = "20:00";
    private String water = "10:30";
    private String movement = "18:00";
    private String winddown = "22:00";

    public ReminderDto() {}

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
}
