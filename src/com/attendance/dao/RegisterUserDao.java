package com.attendance.dao;

import com.attendance.entity.Application;
import com.attendance.entity.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface RegisterUserDao {
    //public List<User> findByUserAll() throws SQLException;
    //添加用户
    public int findByAddUser(User user) throws Exception;
    //查找用户
    public User findByUserName(String username)throws Exception;
    //查找用户补卡信息
    public Application findByUserApplication(String username, String date,String time)throws Exception;
    //添加用户补卡信息
    public int findByAddApplication(Application application)throws Exception;

}
