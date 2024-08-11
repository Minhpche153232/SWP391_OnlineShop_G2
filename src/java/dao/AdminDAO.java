/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.*;
import java.sql.*;
import models.User;

/**
 *
 * @author ASUS
 */
public class AdminDAO extends DBContext {

    public ArrayList<User> getUser() {
        ArrayList<User> x = new ArrayList<>();

        String xSql = "SELECT * FROM User";
        try {
            conn = new DBContext().conn;
            ps = conn.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                User b = new User(rs.getInt("userId"), rs.getString("fullname"),
                        rs.getString("address"), rs.getString("phone"), rs.getString("email"),
                        rs.getString("username"), rs.getBoolean("status"));
                x.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
        ArrayList<User> x = dao.getUser();
        for (User user : x) {
            System.out.println(user);
        }
    }
    }

