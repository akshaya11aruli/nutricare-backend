package com.nutricare.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class SupportDto {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("user_email")
    private String userEmail;

    @NotBlank(message = "Topic is required")
    private String topic;

    @NotBlank(message = "Message is required")
    private String message;

    public SupportDto() {}

    public SupportDto(Long userId, String userEmail, String topic, String message) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.topic = topic;
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
