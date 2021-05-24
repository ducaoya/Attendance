package com.attendance.servlet;

import com.attendance.entity.Attendance;
import com.attendance.entity.User;
import com.attendance.service.LoginService;
import com.attendance.service.LoginServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Costic on 2021/3/23  time:9:53.
 * package:com.attendance.servlet
 * project:Attendance
 */
public class LoginServlet extends BaseServlet {
    LoginService loginService = new LoginServiceImpl();

    // 转换为 json格式
    protected void convertToJson(HttpServletRequest req, HttpServletResponse resp, Object object) throws IOException {

        // 设置解析格式编码
        resp.setContentType("text/html; charset=UTF-8");
        Gson gson = new Gson();
        // 将当前 list容器内部存储的数据转换为 json类型
        String data = gson.toJson(object);
        System.out.println(data);
        // 获得输出流并将JSON格式的对象发送
        resp.getWriter().write(data);

    }


    //登录功能实现
    public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html; charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        User user = loginService.findByUsername(username);
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
	//判断用户状态是管理员还是普通用户，判断完成之后分别跳转对应界面
        if(user.getStatus()==1){
            if (user.getPassword().equals(password)) {
                if(user.getUsername().equals("admin")){
                    req.getRequestDispatcher("admin.html").forward(req, resp);
                }else{
                    req.getRequestDispatcher("index.html").forward(req, resp);
                }
            }else{
                JOptionPane.showMessageDialog(null, "密码错误！");
                req.getRequestDispatcher("login.html").forward(req, resp);
            }
            //未找到用户信息  返回错误信息
        }else{
            JOptionPane.showMessageDialog(null, "账号未激活！");
            req.getRequestDispatcher("login.html").forward(req, resp);
        }
    }
    //查询用户打卡记录
    public void findByUserAttendance(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html; charset=UTF-8");
        String username = req.getParameter("username");
        List<Attendance> attendanceList= loginService.findByUserAttendance(username);
        System.out.println(attendanceList);
        convertToJson(req,resp,attendanceList);

    }
    //上班打卡
    public void clockinUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        resp.setContentType("text/html; charset=UTF-8");

        String username = req.getParameter("username");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
        String time1Demo = req.getParameter("time1");
        String time1 = time1Demo.substring(0, 2) + time1Demo.substring(time1Demo.length() - 2, time1Demo.length());
        String place1Demo = req.getParameter("place1");
        //显示打卡地点，定义一个字符组找到街道中的‘道’，截取第七个字至该字显示出来地点
        char[] place1char = place1Demo.toCharArray();
        int y = 0;
        for (int i = 0; i < place1char.length; i++) {
            if (place1char[i] == '道') {
                y = i;
                break;
            }
        }
        String place1 = place1Demo.substring(6, y + 1);
//        Attendance attendance=loginService.clockinUser(username,date);


            loginService.clockinUserAdd2(username, date, time1, place1);

            JOptionPane.showMessageDialog(null, "亲！您今日打卡已成功，请继续坚持哟！");
            req.getRequestDispatcher("index.html").forward(req, resp);



//        if(attendance==null) {
//            loginService.clockinUserAdd2(username, date, time1, place1);
//            JOptionPane.showMessageDialog(null, "亲！您今日打卡已成功，请继续坚持哟！");
//            req.getRequestDispatcher("index.html").forward(req, resp);
//        }else {
//
//            //判断是否重复打卡，数据库存在记录则显示不能重复打卡，没有且不存在该次打卡时间则打卡成功，没有但存在该次打卡记录则显示不能重复打卡
//            if (attendance.getUsername() == username && attendance.getDate() == date) {
//                if (date.equals("")) {
//                    loginService.clockinUserAdd2(username, date, time1, place1);
//                    JOptionPane.showMessageDialog(null, "不能重复打卡");
//                    req.getRequestDispatcher("index.html").forward(req, resp);
//                } else {
//                    JOptionPane.showConfirmDialog(null, "亲！您今日打卡已成功，请继续坚持哟！");
//                    req.getRequestDispatcher("index.html").forward(req, resp);
//                }
//
//            } else {
//                loginService.clockinUserAdd2(username, date, time1, place1);
//                JOptionPane.showMessageDialog(null, "亲！您今日打卡已成功，请继续坚持哟！");
//                req.getRequestDispatcher("index.html").forward(req, resp);
//            }
//        }
    }

    //下班打卡
    public void clockoutUser (HttpServletRequest req, HttpServletResponse resp) throws Exception {

        resp.setContentType("text/html; charset=UTF-8");
        String username = req.getParameter("username");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
        String time2Demo = req.getParameter("time2");
        String time2 = time2Demo.substring(0, 2) + time2Demo.substring(time2Demo.length() - 2, time2Demo.length());
        String place2Demo = req.getParameter("place2");
        //显示打卡地点，定义一个字符组找到街道中的‘道’，截取第七个字至该字显示出来地点
        char[] place2char = place2Demo.toCharArray();
        int x = 0;
        for (int i = 0; i < place2char.length; i++) {
            if (place2char[i] == '道') {
                x = i;
                break;
            }
        }
        String place2 = place2Demo.substring(6, x + 1);


        loginService.clockinUserAdd(username, date, time2, place2);
        JOptionPane.showMessageDialog(null, "亲！您今日打卡已成功，请继续坚持哟！");
        req.getRequestDispatcher("index.html").forward(req, resp);
//         //判断是否重复打卡，数据库存在记录则显示不能重复打卡，没有且不存在该次打卡时间则打卡成功，没有但存在该次打卡记录则显示不能重复打卡
//         if (attendance.getUsername() == username && attendance.getDate() == date) {
//            if (time2.equals("")) {
//                loginService.clockoutUserAdd(username, date, time2, place2);
//                JOptionPane.showMessageDialog(null, "亲！您今日打卡已成功，请继续坚持哟！");
//                req.getRequestDispatcher("index.html").forward(req, resp);
//            } else {
//                JOptionPane.showConfirmDialog(null, "不能重复打卡");
//                req.getRequestDispatcher("index.html").forward(req, resp);
//            }
//
//        } else {
//            loginService.clockoutUserAdd(username, date, time2, place2);
//            JOptionPane.showMessageDialog(null, "亲！您今日打卡已成功，请继续坚持哟！");
//            req.getRequestDispatcher("index.html").forward(req, resp);
//        }


    }
}
