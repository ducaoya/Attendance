package com.attendance.dao;

import com.attendance.entity.Application;
import com.attendance.entity.User;
import com.attendance.utils.DataSourceUtils;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class RegisterUserDaoImpl implements RegisterUserDao{

    QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

    public int findByAddUser(User user)  throws Exception {
        //创建sql语句，进行数据注册新增操作
        String sql = "insert into userinfo values(?,?,?,?,?,?)";
        int x= queryRunner.update(sql, user.getUsername(),user.getPassword(),user.getName(),user.getSex(),user.getEmail(),user.getStatus());
        return x;
    }

    public User findByUserName(String username)throws Exception{
        //用户数据查询
        String sql = "select * from userinfo where username=?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class),username);
        return user;
        }

    public Application findByUserApplication(String username,String date,String time)throws Exception{
        //用户补卡信息查询
        String sql = "select * from application where username=? and DATE_FORMAT(date,'%Y%m%d')=? and time=?";
        Application application = queryRunner.query(sql, new BeanHandler<Application>(Application.class),username,date,time);
        return application;

    }
    //添加补卡信息
    public int findByAddApplication(Application application)throws Exception{
        String sql = "insert into application values(?,?,?,?)";
        return queryRunner.update(sql,application.getUsername(),application.getDate(),application.getTime(),application.getReason());

    }








    public  List<Application> displayApplication() throws SQLException {
        String sql = "select date from application where username='user3'";
        return queryRunner.query(sql, new BeanListHandler<Application>(Application.class));
    }

}
