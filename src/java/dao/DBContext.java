/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author catmi
 */
public class DBContext {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private final String serverName = "DESKTOP-D0C9604\\Admin";

    private final String dbName = "OnlineShop_SWP391";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "123";

    public DBContext() {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userID, password);
        } catch (Exception e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    public void close() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public Connection getConnection() {
        return conn;
    }
  
    public static void main(String[] args) {
        DBContext con = new DBContext();
        System.out.println(con.getConnection());
    }
}
