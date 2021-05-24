package com.attendance.servlet;

import com.attendance.entity.*;
import com.attendance.service.AdminService;
import com.attendance.service.AdminServiceImpl;
import com.google.gson.Gson;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServlet extends BaseServlet{

    public static final String TIME1 = "08%3A00";

    // 转换为 json格式
    protected void convertToJson(HttpServletRequest req, HttpServletResponse resp, Object object) throws IOException {

        // 设置解析格式编码
        resp.setContentType("text/html; charset=UTF-8");
        Gson gson = new Gson();
        // 将当前 list容器内部存储的数据转换为 json类型
        String data = gson.toJson(object);
        // 获得输出流并将JSON格式的对象发送
        resp.getWriter().write(data);

    }

    // 查询所有非管理员用户信息  ***
    public void queryAllUserInfo(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        resp.setCharacterEncoding("utf-8");

        AdminService adminService = new AdminServiceImpl();
        List<User> userList = adminService.queryAllUserInfo();

        HttpSession session = req.getSession();
        session.setAttribute("userList" ,userList);

        convertToJson(req, resp, userList);
    }

    // 注销用户  ***
    public void logoutUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        req.setCharacterEncoding("utf-8");

        // 获取将要被注销用户的用户名
        String username = req.getParameter("username");

        AdminService adminService = new AdminServiceImpl();

        // 注销用户状态接收变量
        int logoutNotifice = adminService.logoutUser(username);

        if(logoutNotifice == 1){
            JOptionPane.showMessageDialog(null,"注销成功");
        }else {
            JOptionPane.showMessageDialog(null,"注销失败");
        }
        req.getRequestDispatcher("admin.html").forward(req, resp);

    }

    // 查看用户打卡情况 (总打卡次数，全勤次数，缺勤次数)  ***
    public void queryCheckinInfo(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {

        AdminService adminService = new AdminServiceImpl();
        // 获取用户打卡所有的信息
        List<Attendance> list = adminService.queryCheckinInfo();

        List<ApplicationCount> attList = new ArrayList<>();

        // 遍历，取出每一条数据的用户名，查询该用户的全勤次数
        for (int i = 0; i < list.size(); i++) {

            ApplicationCount ac = new ApplicationCount();
            String username = list.get(i).getUsername();

            ac.setUsername(username);
            // 总打卡次数
            UserCount uc1 = adminService.count1(username);
            // 全勤次数
            UserCount uc2 = adminService.count2(username);

            ac.setTotalCount(uc1.getNum());
            ac.setFull(uc2.getNum());
            ac.setNonFull(uc1.getNum() - uc2.getNum());

            attList.add(ac);
        }

        convertToJson(req, resp, attList);

    }

    // 查看用户注册申请 (查询所有状态为 0 用户并显示)  ***
    public void displayUserByStatus(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {

        AdminService adminService = new AdminServiceImpl();
        List<User> statusList = adminService.displayUserByStatus();
        convertToJson(req,resp,statusList);
    }

    // 处理注册申请 (根据用户名修改用户状态)  ***
    public void agreeToApply(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        AdminService adminService = new AdminServiceImpl();
        // 同意注册
        int agreeRegister = adminService.agreeToApply(username);

        if(agreeRegister == 1){
            JOptionPane.showMessageDialog(null,"注册申请处理成功");
        }else {
            JOptionPane.showMessageDialog(null,"注册申请处理成功");
        }
        req.getRequestDispatcher("admin.html").forward(req, resp);

    }

    // 查看用户补卡申请 (查询补卡信息并显示)  ***
    public void displayApplication(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {

        AdminService adminService = new AdminServiceImpl();
        List<Application> appList = adminService.displayApplication();
        convertToJson(req,resp,appList);
    }

    // 同意用户补卡申请
    public void agreeApplication(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String date= concatCharacters(req, resp);
        String time = req.getParameter("time");

        AdminService adminService = new AdminServiceImpl();

        // 根据用户名和密码判断考勤表是否有记录，有则判断时间决定插入time1或time2
        // 没有(说明该用户一天内打卡零次)则创一条新数据
        List<Attendance> list = adminService.queryAttByUnameAndDate(username, date);

        // 考勤表有该用户数据
        if(!list.isEmpty()){

            // 修改用户打卡记录
            int agreeRenewCard = adminService.agreeApplication(username, date);
            if(agreeRenewCard == 1){
                JOptionPane.showMessageDialog(null,"用户打卡记录修改成功");

                // 删除用户打卡申请记录
                int refuseApplication = adminService.refuseApplication(username, date);
                if(refuseApplication  == 1){
                    JOptionPane.showMessageDialog(null,"用户补卡申请记录删除成功");
                }
            }
        }
        // 考勤表没有该用户数据，插入数据
        else{
            if(TIME1.equals(time)){

                int agreeRenewCard = adminService.agreeApplicationOne(username, date, time);
                if(agreeRenewCard == 1){
                    JOptionPane.showMessageDialog(null,"用户考勤信息插入成功");
                }

            }else{

                int agreeRenewCard = adminService.agreeApplicationTwo(username, date, time);
                if(agreeRenewCard == 1){
                    JOptionPane.showMessageDialog(null,"用户考勤信息插入成功");
                }
            }
            // 删除用户打卡申请记录
            int refuseApplication = adminService.refuseApplication(username, date);
            if(refuseApplication == 1){
                JOptionPane.showMessageDialog(null,"用户补卡申请记录删除成功");
            }

        }
        req.getRequestDispatcher("admin.html").forward(req, resp);
    }

    // 拒绝用户补卡申请  ***
    public void refuseApplication(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String date = concatCharacters(req, resp);

        AdminService adminService = new AdminServiceImpl();
        // 拒绝补卡
        int refuseApplication = adminService.refuseApplication(username, date);

        if(refuseApplication == 1){
            JOptionPane.showMessageDialog(null,"补卡申请处理成功");
        }else {
            JOptionPane.showMessageDialog(null,"补卡申请处理失败");
        }
    }

    // 分割并连接字符串
    public String concatCharacters(HttpServletRequest req, HttpServletResponse resp){

        String dt= req.getParameter("date");
        /*String dt = "2021319";*/
        // 将 dt依据 "-"分隔存放到字符数组
        String[] str = dt.split("-");
        String date = str[0];
        // 将字符数组拼接到 date中
        for (int i = 1; i < str.length; i++) {
            date = date.concat(str[i]);
        }
        return date;
    }

}
