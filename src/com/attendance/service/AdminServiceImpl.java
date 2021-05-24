package com.attendance.service;

import com.attendance.dao.AdminDao;
import com.attendance.dao.AdminDaoImpl;
import com.attendance.entity.*;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    AdminDao adminDao = new AdminDaoImpl();

    // 查询所有非管理员用户信息
    @Override
    public List<User> queryAllUserInfo() throws SQLException {

        return adminDao.queryAllUserInfo();
    }

    // 注销用户
    @Override
    public int logoutUser(String username) throws SQLException {

        return adminDao.logoutUser(username);
    }

    // // 查询所有状态为 0 用户并显示
    @Override
    public List<User> displayUserByStatus() throws SQLException {

        return adminDao.displayUserByStatus();
    }

    // 查询补卡信息表并显示
    @Override
    public List<Application> displayApplication() throws SQLException {

        return adminDao.displayApplication();
    }

    // 查询用户打卡信息
    @Override
    public List<Attendance> queryCheckinInfo() throws SQLException {

        return adminDao.queryCheckinInfo();
    }

    // 同意注册申请，修改用户状态
    @Override
    public int agreeToApply(String username) throws SQLException {

        return adminDao.agreeToApply(username);
    }

    // 修改用户打卡记录
    @Override
    public int agreeApplication(String username, String date) throws SQLException {

        return adminDao.agreeApplication(username, date);
    }

    // 删除用户补卡申请
    @Override
    public int refuseApplication(String username, String date) throws SQLException {

        return adminDao.refuseApplication(username, date);
    }

    // 根据用户名和密码判断考勤表是否有记录
    @Override
    public List<Attendance> queryAttByUnameAndDate(String username, String date) throws SQLException {
        return adminDao.queryAttByUnameAndDate(username, date);
    }

    @Override
    public int agreeApplicationOne(String username, String date, String time) throws SQLException {
        return adminDao.agreeApplicationOne(username, date, time);
    }

    @Override
    public int agreeApplicationTwo(String username, String date, String time) throws SQLException {
        return adminDao.agreeApplicationTwo(username, date, time);
    }

    @Override
    public UserCount count1(String username) throws SQLException {
        return adminDao.count1(username);
    }

    @Override
    public UserCount count2(String username) throws SQLException {
        return adminDao.count2(username);
    }

}
