package com.nutricare.ai.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ActivityLogId implements Serializable {
    private Long userId;
    private LocalDate logDate;

    public ActivityLogId() {}

    public ActivityLogId(Long userId, LocalDate logDate) {
        this.userId = userId;
        this.logDate = logDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityLogId that = (ActivityLogId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(logDate, that.logDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, logDate);
    }
}
