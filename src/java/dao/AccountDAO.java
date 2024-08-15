/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Role;
import models.User;

/**
 *
 * @author Admin
 */
public class AccountDAO {
    UserDAO userDAO = new UserDAO();
    public User login(String userName, String password) {
        DBContext dBContext = new DBContext();
        String sql = "SELECT userId ,[fullname],[address],[phone],[email],[username],[dob],[balance],r.rolename ,[status], [avatar], [gender] FROM [dbo].[User] as u \n"
                + "inner join [dbo].[Role] as r on r.roleId = u.roleId\n"
                + "WHERE [username] = ? AND password = ?";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, userDAO.getMd5(password));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setUserName(rs.getString("username"));
                u.setDob(rs.getString("dob"));
                u.setBalance(rs.getFloat("balance"));
                u.setRole(rs.getString("roleName").trim());
                u.setStatus(rs.getBoolean("status"));
                u.setAvatar(rs.getString("avatar"));
                u.setGender(rs.getBoolean("gender"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public User getUser(int id) {
        DBContext dBContext = new DBContext();
        String sql = "SELECT userId ,[fullname],[address],[phone],[email],[username],[dob],[balance],r.rolename ,[status], [avatar], [gender] FROM [dbo].[User] as u \n"
                + "inner join [dbo].[Role] as r on r.roleId = u.roleId\n"
                + "WHERE userId = ?";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setUserName(rs.getString("username"));
                u.setDob(rs.getString("dob"));
                u.setBalance(rs.getFloat("balance"));
                u.setRole(rs.getString("roleName").trim());
                u.setStatus(rs.getBoolean("status"));
                u.setAvatar(rs.getString("avatar"));
                u.setGender(rs.getBoolean("gender"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean changeProfile(User user) {
        boolean check = false;
        DBContext dBContext = new DBContext();
        String sql = "UPDATE [dbo].[User] SET fullname = ?, address = ?, phone = ?, email=?, username =?, dob = ?,"
                + " balance = ?, avatar = ?, gender = ? "
                + "WHERE userId = ?";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setString(1, user.getFullname());
            statement.setString(2, user.getAddress());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getUserName());
            statement.setString(6, user.getDob());
            statement.setFloat(7, user.getBalance());
            statement.setString(8, user.getAvatar());
            statement.setBoolean(9, user.isGender());
            statement.setInt(10, user.getUserId());

            statement.executeUpdate();
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean changePassword(int id, String oldPass, String newPass) {
        boolean check = false;
        DBContext dBContext = new DBContext();
        String sql = "UPDATE [dbo].[User] SET password = ? "
                + " WHERE userId = ? AND password = ?";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setString(1, userDAO.getMd5(newPass));
            statement.setInt(2, id);
            statement.setString(3, userDAO.getMd5(oldPass));

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean checkUser(int id, String userName) {
        boolean check = false;
        DBContext dBContext = new DBContext();
        String sql = "SELECT * FROM [dbo].[User] "
                + " WHERE userId != ? AND username = ?";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, userName);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                check = true;

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public int countTotalPage(String textSearch) {
        int total = 0;
        DBContext dBContext = new DBContext();
        String sql = "SELECT \n"
                + "    COUNT(*) / 20 + CASE WHEN COUNT(*) % 20 > 0 THEN 1 ELSE 0 END AS totalPages\n"
                + "FROM \n"
                + "    [OnlineShop_SWP391].[dbo].[User] u\n"
                + "JOIN \n"
                + "    [OnlineShop_SWP391].[dbo].[Role] r \n"
                + "ON \n"
                + "    u.roleId = r.roleId\n"
                + "WHERE \n"
                + "    u.roleId != 1 \n"
                + "    u.fullname LIKE '%' + ? + '%' \n"
                + "    OR u.username LIKE '%' + ? + '%';";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setString(1, textSearch);
            statement.setString(2, textSearch);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return total;
    }

    public List<User> searchUser(String textSearch, int pageIndex, int roleId) {
        List<User> list = null;
        DBContext dBContext = new DBContext();
        String sql
                = """
                  WITH SearchResults AS (
                      SELECT u.*, r.rolename,
                             ROW_NUMBER() OVER (ORDER BY u.userId) AS RowNum
                      FROM [OnlineShop_SWP391].[dbo].[User] u
                      JOIN [OnlineShop_SWP391].[dbo].[Role] r 
                      ON u.roleId = r.roleId
                      WHERE (u.fullname LIKE '%' + ? + '%' 
                      OR u.username LIKE '%' + ? + '%' ) AND u.roleId != 1 AND u.roleId = ? 
                  )
                  SELECT *
                  FROM SearchResults
                  WHERE RowNum BETWEEN (?-1)*20 AND 20*? ;
                  """;
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setString(1, textSearch);
            statement.setString(2, textSearch);
            statement.setInt(3, roleId);
            statement.setInt(4, pageIndex);
            statement.setInt(5, pageIndex);

            ResultSet rs = statement.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setUserName(rs.getString("username"));
                u.setDob(rs.getString("dob"));
                u.setBalance(rs.getFloat("balance"));
                u.setRole(rs.getString("roleName").trim());
                u.setStatus(rs.getBoolean("status"));
                u.setAvatar(rs.getString("avatar"));
                u.setGender(rs.getBoolean("gender"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<User> searchUser(String textSearch, int pageIndex, int roleId, boolean satus) {
        List<User> list = null;
        DBContext dBContext = new DBContext();
        String sql
                = """
                  WITH SearchResults AS (
                      SELECT u.*, r.rolename,
                             ROW_NUMBER() OVER (ORDER BY u.userId) AS RowNum
                      FROM [OnlineShop_SWP391].[dbo].[User] u
                      JOIN [OnlineShop_SWP391].[dbo].[Role] r 
                      ON u.roleId = r.roleId
                      WHERE (u.fullname LIKE '%' + ? + '%' 
                      OR u.username LIKE '%' + ? + '%' ) AND u.roleId != 1 AND u.roleId = ? AND u.status = ? 
                  )
                  SELECT *
                  FROM SearchResults
                  WHERE RowNum BETWEEN (?-1)*20 AND 20*? ;
                  """;
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setString(1, textSearch);
            statement.setString(2, textSearch);
            statement.setInt(3, roleId);
            statement.setBoolean(4, satus);
            statement.setInt(5, pageIndex);
            statement.setInt(6, pageIndex);

            ResultSet rs = statement.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setUserName(rs.getString("username"));
                u.setDob(rs.getString("dob"));
                u.setBalance(rs.getFloat("balance"));
                u.setRole(rs.getString("roleName").trim());
                u.setStatus(rs.getBoolean("status"));
                u.setAvatar(rs.getString("avatar"));
                u.setGender(rs.getBoolean("gender"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<User> searchUser(String textSearch, int pageIndex, boolean satus) {
        List<User> list = null;
        DBContext dBContext = new DBContext();
        String sql
                = """
                  WITH SearchResults AS (
                      SELECT u.*, r.rolename,
                             ROW_NUMBER() OVER (ORDER BY u.userId) AS RowNum
                      FROM [OnlineShop_SWP391].[dbo].[User] u
                      JOIN [OnlineShop_SWP391].[dbo].[Role] r 
                      ON u.roleId = r.roleId
                      WHERE (u.fullname LIKE '%' + ? + '%' 
                      OR u.username LIKE '%' + ? + '%' ) AND u.roleId != 1 AND u.status = ? 
                  )
                  SELECT *
                  FROM SearchResults
                  WHERE RowNum BETWEEN (?-1)*20 AND 20*? ;
                  """;
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setString(1, textSearch);
            statement.setString(2, textSearch);
            statement.setBoolean(3, satus);
            statement.setInt(4, pageIndex);
            statement.setInt(5, pageIndex);

            ResultSet rs = statement.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setUserName(rs.getString("username"));
                u.setDob(rs.getString("dob"));
                u.setBalance(rs.getFloat("balance"));
                u.setRole(rs.getString("roleName").trim());
                u.setStatus(rs.getBoolean("status"));
                u.setAvatar(rs.getString("avatar"));
                u.setGender(rs.getBoolean("gender"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<User> searchUser(String textSearch, int pageIndex) {
        List<User> list = null;
        DBContext dBContext = new DBContext();
        String sql
                = """
                  WITH SearchResults AS (
                      SELECT u.*, r.rolename,
                             ROW_NUMBER() OVER (ORDER BY u.userId) AS RowNum
                      FROM [OnlineShop_SWP391].[dbo].[User] u
                      JOIN [OnlineShop_SWP391].[dbo].[Role] r 
                      ON u.roleId = r.roleId
                      WHERE (u.fullname LIKE '%' + ? + '%' 
                      OR u.username LIKE '%' + ? + '%' ) AND u.roleId != 1
                  )
                  SELECT *
                  FROM SearchResults
                  WHERE RowNum BETWEEN (?-1)*20 AND 20*? ;
                  """;
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setString(1, textSearch);
            statement.setString(2, textSearch);
            statement.setInt(3, pageIndex);
            statement.setInt(4, pageIndex);

            ResultSet rs = statement.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setUserName(rs.getString("username"));
                u.setDob(rs.getString("dob"));
                u.setBalance(rs.getFloat("balance"));
                u.setRole(rs.getString("roleName").trim());
                u.setStatus(rs.getBoolean("status"));
                u.setAvatar(rs.getString("avatar"));
                u.setGender(rs.getBoolean("gender"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<Role> getAllRole() {
        List<Role> list = null;
        DBContext dBContext = new DBContext();
        String sql
                = "SELECT * FROM [OnlineShop_SWP391].[dbo].[Role] as r WHERE r.roleId != 1";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Role r = new Role();
                r.setRoleId(rs.getInt(1));
                r.setRoleName(rs.getString(2));

                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public boolean changeRoleUser(int value, int userId) {
        boolean check = false;
        DBContext dBContext = new DBContext();
        String sql = "UPDATE [dbo].[User] SET roleId = ? "
                + " WHERE userId = ?";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setInt(1, value);
            statement.setInt(2, userId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean changeStatusUser(boolean value, int userId) {
        boolean check = false;
        DBContext dBContext = new DBContext();
        String sql = "UPDATE [dbo].[User] SET status = ? "
                + " WHERE userId = ?";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setBoolean(1, value);
            statement.setInt(2, userId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        System.out.println(accountDAO.searchUser("", 1, 2));
    }
}
