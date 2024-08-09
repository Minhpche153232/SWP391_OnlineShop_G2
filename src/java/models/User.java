/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class User {
    private Integer userId;
    private String fullname;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;
    private Date dob;
    private Integer balance;
    private Integer roleId;
    private Boolean status;
    private String avatar;
    
    public User(int aInt, String string, String string1, String string2, String string3, String string4, String string5) {
    }

    public User(Integer userId, String fullname, String address, String phone, 
            String email, String username, String password, Date dob,  Integer balance,
            Integer roleId, Boolean status, String avatar ) {
        this.userId  = userId;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.balance = balance;
        this.roleId = roleId;
        this.status = status;
        this.avatar = avatar;
   }
      public User(String fullname, String address, String phone, 
            String email, String username, String password, Date dob,  Integer balance,
            Integer roleId, Boolean status, String avatar ) {
        this.userId  = null;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.balance = balance;
        this.roleId = roleId;
        this.status = status;
        this.avatar = avatar;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" + "fullname=" + fullname + ", username=" + username + ", password=" + password + '}';
    }
  
}
