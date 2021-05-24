package com.attendance.servlet;

import com.attendance.entity.Application;
import com.attendance.entity.User;
import com.attendance.service.RegisterUserService;
import com.attendance.service.RegisterUserServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;


public class RegisterUserServlet extends BaseServlet{

    RegisterUserService registerUserService = new RegisterUserServiceImpl();
    //用户注册
    public void findbyAddUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        

        User user = registerUserService.findByUserName(username);

        //判断数据库中用户是否存在。
        if (user==null){
            //无用户数据，向数据库添加注册信息
            registerUserService.findByAddUser(username, password, name, sex, email);
            JOptionPane.showMessageDialog(null, "注册已提交！等待管理员审核");
            request.getRequestDispatcher("login.html").forward(request, response);


        } else if(user.getUsername().equals(username)) {
            //用户名重名，返回注册界面
            JOptionPane.showMessageDialog(null, "用户名已存在");

            request.getRequestDispatcher("register.html").forward(request, response);
        } else {
            //无重复用户，向数据库添加注册信息
            registerUserService.findByAddUser(username, password, name, sex, email);
            JOptionPane.showMessageDialog(null, "注册已提交！等待管理员审核");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }



    //添加补卡信息
    public void findByAddApplication(HttpServletRequest request, HttpServletResponse response) throws Exception {

        AdminServlet adminServlet = new AdminServlet();

        String username = request.getParameter("username");
        String date  = request.getParameter("date");
        String date1 = adminServlet.concatCharacters(request,response);

        String timeDemo = request.getParameter("time");
        String time = timeDemo.substring(0,2) +":"+ timeDemo.substring(timeDemo.length()-2,timeDemo.length());
        String reason = request.getParameter("reason");


        Application application = registerUserService.findByUserApplication(username,date1,time);

        if (application!=null){
            if (application.getTime().equals(time)){
                //重复打卡
                JOptionPane.showMessageDialog(null, "请勿重复补卡！");
                request.getRequestDispatcher("index.html").forward(request, response);
            }
            else {
            //无当天补卡数据，向数据库添加补卡信息，转跳到index
            registerUserService.findByAddApplication(username,date,time,reason);
            JOptionPane.showMessageDialog(null, "补卡成功！等待管理员处理");
            request.getRequestDispatcher("index.html").forward(request, response);
            }
        }else{
            //无当天打卡数据
            registerUserService.findByAddApplication(username,date,time,reason);
            JOptionPane.showMessageDialog(null, "补卡成功！等待管理员处理");
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

}
