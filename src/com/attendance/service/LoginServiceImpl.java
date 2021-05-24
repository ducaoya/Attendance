package com.attendance.service;

import com.attendance.dao.LoginUserDao;
import com.attendance.dao.LoginUserDaoImpl;
import com.attendance.entity.Attendance;
import com.attendance.entity.User;

import javax.swing.event.ListDataEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Costic on 2021/3/23  time:9:35.
 * package:com.attendance.service
 * project:Attendance
 */
public class LoginServiceImpl implements LoginService{
    LoginUserDao loginUserDao=new LoginUserDaoImpl();
    //登录
    @Override
    public User findByUsername(String username)throws Exception {
        User user=loginUserDao.findByUsername(username);
        return user;
    }

    //上班打卡
    @Override
    public Attendance clockinUser(String username, Date date) throws Exception{
        Attendance attendance=loginUserDao.clockinUser(username,date);
        return attendance;
    }
    //下班打卡
    public Attendance clockoutUser(String username, Date date) throws Exception{
        Attendance attendance=loginUserDao.clockinUser(username,date);
        return attendance;
    }

    //打卡记录查询
    @Override
    public List<Attendance> findByUserAttendance(String username) throws Exception {
        return loginUserDao.findByUserAttendace(username);
    }
    //上班将数据传入数据库
    @Override
    public int clockinUserAdd2(String username,Date date,String time1,String place1) throws  Exception{
        Attendance attendance=new Attendance(username,date,time1,place1,null,null);
        return  loginUserDao.clockinUserAdd2(attendance);
    }
    //下班
    @Override
    public int clockinUserAdd(String username,Date date,String time2,String place2) throws  Exception{
        Attendance attendance=new Attendance(username,date,null,null,time2,place2);
        return loginUserDao.clockinUserAdd(attendance);
    }

}
