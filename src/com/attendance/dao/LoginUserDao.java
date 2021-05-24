package com.attendance.dao;

import com.attendance.entity.Attendance;
import com.attendance.entity.User;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Costic on 2021/3/23  time:9:58.
 * package:com.attendance.dao
 * project:Attendance
 */
public interface LoginUserDao {
    //查询用户名及密码校验是否匹配成功 成功弹出管理界面或者用户界面  失败返回信息
    User findByUsername(String username) throws Exception;
    //上班打卡
    Attendance clockinUser(String username, Date date)throws Exception;
    //下班打卡
    Attendance clockoutUser(String username,Date date)throws Exception;
    //打卡记录查询
    List<Attendance> findByUserAttendace(String username)throws Exception;


    int clockinUserAdd(Attendance attendance)throws Exception;

    int clockinUserAdd2(Attendance attendance)throws  Exception;
}
