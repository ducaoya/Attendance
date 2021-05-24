package com.attendance.service;

import com.attendance.entity.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface AdminService {

    // 查询所有非管理员用户信息
    List<User> queryAllUserInfo() throws SQLException;

    // 注销用户
    int logoutUser(String username) throws SQLException;

    // 查询所有状态为 0 用户并显示
    List<User> displayUserByStatus() throws SQLException;

    // 查询补卡信息表并显示
    List<Application> displayApplication() throws SQLException;

    // 查询用户打卡信息
    List<Attendance> queryCheckinInfo() throws SQLException;

    // 同意注册申请，修改用户状态
    int agreeToApply(String username) throws SQLException;

    // 修改用户打卡记录
    int agreeApplication(String username, String date) throws SQLException;

    // 删除用户补卡申请
    int refuseApplication(String username, String date) throws SQLException;

    // 根据用户名和密码判断考勤表是否有记录
    List<Attendance> queryAttByUnameAndDate(String username, String date) throws SQLException;

    int agreeApplicationOne(String username, String date, String time) throws SQLException;

    int agreeApplicationTwo(String username, String date, String time) throws SQLException;

    UserCount count1(String username) throws SQLException;

    UserCount count2(String username) throws SQLException;
}



