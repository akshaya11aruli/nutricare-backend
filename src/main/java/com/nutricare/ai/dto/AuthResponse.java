package com.nutricare.ai.dto;

public class AuthResponse {
    private boolean success;
    private String message;
    private String token;
    private UserInfo user;

    public static class UserInfo {
        private Long id;
        private String name;
        private String email;

        public UserInfo() {}

        public UserInfo(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public AuthResponse() {}

    public AuthResponse(boolean success, String message, String token, UserInfo user) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.user = user;
    }

    public static AuthResponse ok(String message, String token, UserInfo user) {
        return new AuthResponse(true, message, token, user);
    }

    public static AuthResponse error(String message) {
        return new AuthResponse(false, message, null, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
