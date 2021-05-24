package com.attendance.dao;

import com.attendance.entity.Attendance;
import com.attendance.entity.User;
import com.attendance.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Costic on 2021/3/23  time:9:57.
 * package:com.attendance.dao
 * project:Attendance
 */

public class LoginUserDaoImpl implements LoginUserDao {
    QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public User findByUsername(String username)throws Exception{
        String sql = "select * from userinfo where username=?";

            return queryRunner.query(sql,new BeanHandler<User>(User.class),username);

    }
    //上班打卡
    @Override
    public Attendance clockinUser(String username, Date date) throws Exception{
        String sql = "select * from attendance where username=? and date=?";
        return queryRunner.query(sql,new BeanHandler<Attendance>(Attendance.class),username,date);

    }
    //下班打卡
    @Override
    public Attendance clockoutUser(String username,Date date)throws Exception{
        String sql="select * from attendance";
        return queryRunner.query(sql,new BeanHandler<Attendance>(Attendance.class),username);
    }


    //打卡记录查询
    public List<Attendance> findByUserAttendace(String username)throws Exception{
        String sql = "select * from attendance where username = ?";
        return queryRunner.query(sql, new BeanListHandler<Attendance>(Attendance.class),username);
    }
    //下班添加数据进入数据库
    @Override
    public int clockinUserAdd(Attendance attendance)throws Exception{

        String sql="insert into attendance values (?,?,?,?,?,?)";
//        update attendance set username=?,date=?,time2=?,place2=? where username=? and date=?
        return queryRunner.update(sql,attendance.getUsername(),attendance.getDate() ,null,null,attendance.getTime2(),attendance.getPlace2());
    }
    //上班数据
    public int clockinUserAdd2(Attendance attendance)throws Exception{

        String sql="insert into attendance values (?,?,?,?,?,?)";
        return queryRunner.update(sql,attendance.getUsername(),attendance.getDate() ,attendance.getTime1(),attendance.getPlace1(),null,null);
    }

}
