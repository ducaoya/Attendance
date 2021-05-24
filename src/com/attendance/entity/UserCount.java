package com.attendance.entity;

public class UserCount {
    private int num;

    public UserCount() {
    }

    public UserCount(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "UserCount{" +
                "num=" + num +
                '}';
    }
}
