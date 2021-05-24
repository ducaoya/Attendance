package com.attendance.service;

import com.attendance.entity.Application;
import com.attendance.entity.User;

import java.util.Date;

public interface RegisterUserService {
    public int findByAddUser(String username, String password, String name, String sex, String email)throws Exception;
    public User findByUserName(String username) throws Exception;
    public Application findByUserApplication(String username, String date,String time)throws Exception;
    public int findByAddApplication(String username,String date,String time,String reason)throws Exception;

}
