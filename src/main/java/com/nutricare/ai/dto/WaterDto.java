package com.nutricare.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class WaterDto {

    @JsonProperty("user_id")
    private Long userId;

    private Integer glasses;

    @JsonProperty("log_date")
    private LocalDate logDate;

    public WaterDto() {}

    public WaterDto(Long userId, Integer glasses) {
        this.userId = userId;
        this.glasses = glasses;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getGlasses() {
        return glasses;
    }

    public void setGlasses(Integer glasses) {
        this.glasses = glasses;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }
}
