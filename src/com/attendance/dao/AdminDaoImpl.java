package com.attendance.dao;

import com.attendance.entity.*;
import com.attendance.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

    // 查询所有非管理员用户信息
    @Override
    public List<User> queryAllUserInfo() throws SQLException {

        String sql = "select* from userinfo where username!='admin'";

        // 将结果集中的第一行数据封装到 User实例中
        return queryRunner.query(sql, new BeanListHandler<User>(User.class));
    }

    // 注销用户
    @Override
    public int logoutUser(String username) throws SQLException {

        String sql = "delete from userinfo where username=?";
        return queryRunner.update(sql,username);
    }

    // 查询所有状态为 0 用户并显示
    @Override
    public List<User> displayUserByStatus() throws SQLException {

        String sql = "select* from userinfo where status=0";
        return queryRunner.query(sql, new BeanListHandler<User>(User.class));
    }

    // 查询用户打卡信息
    @Override
    public List<Attendance> queryCheckinInfo() throws SQLException {

        String sql = "select DISTINCT username from attendance ORDER BY username";
        return queryRunner.query(sql, new BeanListHandler<Attendance>(Attendance.class));
    }

    // 查询补卡信息表并显示
    @Override
    public List<Application> displayApplication() throws SQLException {

        String sql = "select* from application";
        return queryRunner.query(sql, new BeanListHandler<Application>(Application.class));
    }

    // 同意注册申请，修改用户状态
    @Override
    public int agreeToApply(String username) throws SQLException {

        String sql = "update userinfo set status=1 where username=?";
        return queryRunner.update(sql, username);
    }

    // 修改用户打卡记录
    @Override
    public int agreeApplication(String username, String date) throws SQLException {

        String sql = "update attendance set time1='08:00:00',time2='18:00:00',place1='XXX',place2='XXX' where username=? and date=?";
        return queryRunner.update(sql, username, date);
    }

    // 删除用户补卡申请
    @Override
    public int refuseApplication(String username, String date) throws SQLException {

        String sql = "delete from application where username=? and DATE_FORMAT(date,'%Y%m%d')=?";

        return queryRunner.update(sql, username, date);
    }

    // 根据用户名和密码判断考勤表是否有记录
    @Override
    public List<Attendance> queryAttByUnameAndDate(String username, String date) throws SQLException {

        String sql = "select* from attendance where username=? and DATE_FORMAT(date,'%Y%m%d')=?";
        return queryRunner.query(sql, new BeanListHandler<Attendance>(Attendance.class),username, date);
    }

    // 插入补卡用户信息
    @Override
    public int agreeApplicationOne(String username, String date, String time) throws SQLException {

        String sql = "insert into attendance values(?,?,?,?,?,?)";
        return queryRunner.update(sql,username,date,time,"XXX",null,"XXX");
    }

    // 插入补卡用户信息
    @Override
    public int agreeApplicationTwo(String username, String date, String time) throws SQLException {

        String sql = "insert into attendance values(?,?,?,?,?,?)";
        return queryRunner.update(sql,username,date,null,"XXX",time,"XXX");
    }

    // 查询全勤次数
    @Override
    public UserCount count1(String username) throws SQLException {

        String sql="select count(*) as num from attendance where username=?";
        return queryRunner.query(sql, new BeanHandler<UserCount>(UserCount.class), username);
    }

    // 查询非全勤次数
    @Override
    public UserCount count2(String username) throws SQLException {

        String sql="select count(*) as num from attendance where username=? and time1 is not null and time2 is not null";
        return queryRunner.query(sql, new BeanHandler<UserCount>(UserCount.class), username);
    }

}
