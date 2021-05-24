package com.attendance.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Attendance {
    private String username;
    private Date date;
    private String time1;
    private String place1;
    private String time2;
    private String place2;

    public Attendance() {
    }

    public Attendance(String username, Date date, String time1, String place1, String time2, String place2) {
        this.username = username;
        this.date = date;
        this.time1 = time1;
        this.place1 = place1;
        this.time2 = time2;
        this.place2 = place2;
    }

}
