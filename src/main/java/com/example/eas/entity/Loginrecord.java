package com.example.eas.entity;

public class Loginrecord {
    private String time;

    private String username;

    private Integer role;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Loginrecord() {
    }

    @Override
    public String toString() {
        return "Loginrecord{" +
                "time='" + time + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}