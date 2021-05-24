package com.attendance.entity;

public class User {

   private String username;
   private String password;
   private String name;
   private String sex;
   private String email;
   private int status;

   public User() {
   }

   public User(String username, String password, String name, String sex, String email, int status) {
      this.username = username;
      this.password = password;
      this.name = name;
      this.sex = sex;
      this.email = email;
      this.status = status;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSex() {
      return sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   @Override
   public String toString() {
      return "User{" +
              "username='" + username + '\'' +
              ", password='" + password + '\'' +
              ", name='" + name + '\'' +
              ", sex='" + sex + '\'' +
              ", email='" + email + '\'' +
              ", status=" + status +
              '}';
   }
}
