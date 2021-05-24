package com.attendance.entity;

public class ApplicationCount {
    private String username;
    private int totalCount;
    private int full;
    private int nonFull;

    public ApplicationCount() {
    }

    public ApplicationCount(String username, int totalCount, int full, int nonFull) {
        this.username = username;
        this.totalCount = totalCount;
        this.full = full;
        this.nonFull = nonFull;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getFull() {
        return full;
    }

    public void setFull(int full) {
        this.full = full;
    }

    public int getNonFull() {
        return nonFull;
    }

    public void setNonFull(int nonFull) {
        this.nonFull = nonFull;
    }

    @Override
    public String toString() {
        return "ApplicationCount{" +
                "username='" + username + '\'' +
                ", totalCount=" + totalCount +
                ", full=" + full +
                ", nonFull=" + nonFull +
                '}';
    }
}
