/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
/**
 *
 * @author Admin
 */
public class UserDAO extends DBContext {

    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM [User] WHERE username = ? AND password = ? and status = 'true'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDob(rs.getDate("dob"));
                user.setBalance(rs.getInt("balance"));
                user.setRoleId(rs.getInt("roleId"));
                user.setStatus(rs.getBoolean("status"));
                user.setAvatar(rs.getString("avatar"));
                user.setGender(rs.getBoolean("gender"));
            }
        } catch (SQLException e) {
            System.out.println("getUserByUsernameAndPassword: " + e.getMessage());
        }
        return user;
    }

    public boolean createUser(User user) {
        String sql = "INSERT INTO [User] (fullname, username, password, email, phone, dob, address, gender, balance, roleId, status, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getFullname());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
            statement.setDate(6, new Date(user.getDob().getTime())); // Convert java.util.Date to java.sql.Date
            statement.setString(7, user.getAddress());
            statement.setBoolean(8, user.getGender());
            statement.setInt(9, user.getBalance());
            statement.setInt(10, user.getRoleId());
            statement.setBoolean(11, user.getStatus());
            statement.setString(12, user.getAvatar());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("createUser: " + e.getMessage());;
        }
        return false;
    }

    public boolean usernameExists(String username) {
        String sql = "SELECT 1 FROM [User] WHERE username = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM [User] WHERE email = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean phoneExists(String phone) {
        String sql = "SELECT 1 FROM [User] WHERE phone = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

