/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ASUS
 */
public class User {

    private int userId;
    private String avatar;
    private String userName;
    private String password;
    private String fullname;
    private String address;
    private String email;
    private String phone;
    private String dob;
    private float balance;
    private boolean status;
    private boolean gender;
    private String role;

    public User() {
    }

    public User(int userId, String avatar, String userName, String password, String fullname, String address, String email, String phone, String dob, float balance, boolean status, boolean gender, String role) {
        this.userId = userId;
        this.avatar = avatar;
        this.userName = userName;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.balance = balance;
        this.status = status;
        this.gender = gender;
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", avatar=" + avatar + ", userName=" + userName + ", password=" + password + ", fullname=" + fullname + ", address=" + address + ", email=" + email + ", phone=" + phone + ", dob=" + dob + ", balance=" + balance + ", status=" + status + ", gender=" + gender + ", role=" + role + '}';
    }


}
