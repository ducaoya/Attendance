package com.attendance.service;

import com.attendance.dao.RegisterUserDao;
import com.attendance.dao.RegisterUserDaoImpl;
import com.attendance.entity.Application;
import com.attendance.entity.User;

import java.util.Date;
    //创建注册账户方法
public class RegisterUserServiceImpl implements RegisterUserService{
    RegisterUserDao registerUserDao = new RegisterUserDaoImpl();

    //添加用户
    public int findByAddUser(String username, String password, String name, String sex, String email) throws Exception{

        User user = new User(username,password,name,sex,email,0);
        return registerUserDao.findByAddUser(user);
    }

    //查找用户是否存在
    public User findByUserName(String username) throws Exception {

        User user =registerUserDao.findByUserName(username);
        return user;
    }

    //查找补卡记录是否存在
    public Application findByUserApplication(String username,String date,String time)throws Exception{

        Application application =registerUserDao.findByUserApplication(username,date,time);
        return application;
    }
    //添加补卡记录
    public int findByAddApplication(String username,String date,String time,String reason)throws Exception{
        Application application = new Application(username,date,time,reason);
        return registerUserDao.findByAddApplication(application);
    }
}

