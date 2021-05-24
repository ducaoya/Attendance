package com.attendance.service;

import com.attendance.entity.Attendance;
import com.attendance.entity.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Costic on 2021/3/23  time:9:49.
 * package:com.attendance.service
 * project:Attendance
 */
public interface LoginService {

    //查询前端传输的用户名与密码是否匹配  成功显示界面  管理员或者用户  失败返回信息
    User findByUsername(String username) throws Exception;
    //上班打卡
    Attendance clockinUser(String username, Date date) throws Exception;
    //下班打卡
    Attendance clockoutUser(String username, Date date) throws Exception;


    //打卡记录查询
    List<Attendance> findByUserAttendance(String username) throws Exception;
    //上班添加数据进入数据库
    int clockinUserAdd(String username, Date date, String time2, String place2)throws Exception;
    int clockinUserAdd2(String username, Date date, String time1, String place1)throws Exception;
}
