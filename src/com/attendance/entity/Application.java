package com.attendance.entity;

public class Application {
    private String username;
    private String date;
    private String time;

    private String reason;

    public Application() {
    }

    public Application(String username, String date, String time, String reason) {
        this.username = username;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReason() {
        return reason;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Application{" +
                "username='" + username + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
