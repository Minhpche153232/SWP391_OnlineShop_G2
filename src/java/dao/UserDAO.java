/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Admin
 */
public class UserDAO extends DBContext {

     public boolean deleteAccount( String email){
         boolean check = false;
        try {
            String query = "DELETE FROM [User] where email = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
     public User getUserByMailAndPassword(String mail) {
        User user = null;
        String sql = "SELECT * FROM [User] WHERE email = ?  and status = 'false'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mail);
          
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDob(rs.getString("dob"));
                user.setBalance(rs.getInt("balance"));
                user.setRole(rs.getString("roleId"));
                user.setStatus(rs.getBoolean("status"));
                user.setAvatar(rs.getString("avatar"));
                user.setGender(rs.getBoolean("gender"));
            }
        } catch (SQLException e) {
            System.out.println("getUserByUsernameAndPassword: " + e.getMessage());
        }
        return user;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM [User] WHERE username = ? AND password = ? and status = 'true'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, getMd5(password));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setFullname(rs.getString("fullname"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDob(rs.getString("dob"));
                user.setBalance(rs.getInt("balance"));
                user.setRole(rs.getString("roleId"));
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
        String sql = "INSERT INTO [User] (fullname, username, password, email, phone, dob, address, gender, balance, roleId, status, avatar)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getFullname());
            statement.setString(2, user.getUserName());
            statement.setString(3, getMd5(user.getPassword())); /// ma hoa in here
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getDob()); // Convert java.util.Date to java.sql.Date
            statement.setString(7, user.getAddress());
            statement.setBoolean(8, user.isGender());
            statement.setFloat(9, user.getBalance());
            statement.setString(10, user.getRole());
            statement.setBoolean(11, user.isStatus());
            statement.setString(12, user.getAvatar());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("createUser: " + e.getMessage());
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

    public String getMd5(String input) // Mã hóa với MD5 - PARAM - PASSWORD
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailForResetPW(String txtInput) {
        try {
            String query = "select email from [User] where email = ? or username = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, txtInput);
            ps.setString(2, txtInput);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("email");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(String password, String email) {
        try {
            String query = "update [User] set [password] = ? where email = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, getMd5(password));
            ps.setString(2, email);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        System.out.println(dao.getMd5("12345678"));
    }

    public boolean updateUserBalance(User user) {
        String sql = "UPDATE [User] SET balance = ? WHERE userId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, user.getBalance());
            stmt.setInt(2, user.getUserId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
