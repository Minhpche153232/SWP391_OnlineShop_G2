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
import models.Order;
import models.OrderItem;
import models.Product;
import models.User;

/**
 *
 * @author ASUS
 */
public class OrderDAO {

    public List<OrderItem> getMyOrder(int userId, String search) {
        List<OrderItem> orderItems = null;
        DBContext dBContext = new DBContext();
        StringBuilder sql = new StringBuilder("SELECT tb1.*, p.productName, p.price, (p.price * tb1.quantity) AS totalPrice FROM "
                + "(SELECT o.orderDate, o.shipAddress, o.shipDate, o.status, o.toAddress, o.totalPrice AS totalPriceOrder, "
                + "od.orderId, od.color, od.image, od.productId, od.quantity, od.size, o.customerId "
                + "FROM [dbo].[Order] o "
                + "JOIN [dbo].[OrderDetails] od ON o.orderId = od.orderId) AS tb1 "
                + "JOIN Product p ON p.productId = tb1.productId "
                + "WHERE tb1.customerId = ?");

        // Thêm điều kiện tìm kiếm theo orderId hoặc productName nếu search không rỗng hoặc null
        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND (tb1.orderId LIKE ? OR p.productName LIKE ?)");
        }

        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql.toString());
            statement.setInt(1, userId);

            // Set giá trị cho các tham số tìm kiếm nếu search không rỗng hoặc null
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search + "%";
                statement.setString(2, searchPattern);
                statement.setString(3, searchPattern);
            }

            ResultSet rs = statement.executeQuery();
            orderItems = new ArrayList<>();

            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                Product product = new Product();
                Order order = new Order();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getFloat("price"));

                orderItem.setColor(rs.getString("color"));
                orderItem.setImage(rs.getString("image"));
                orderItem.setSize(rs.getInt("size"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setTotalPrice(rs.getFloat("totalPrice")); // Calculated total price

                orderItem.setProduct(product);
                order.setOrderDate(rs.getString("orderDate"));
                order.setOrderId(rs.getInt("orderId"));
                order.setShipDate(rs.getString("shipDate"));
                order.setStatus(rs.getString("status"));
                order.setTotalPrice(rs.getFloat("totalPriceOrder"));
                order.setToAddress(rs.getString("toAddress"));
                orderItem.setOrder(order);

                orderItems.add(orderItem);
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
        return orderItems;
    }

    public List<Order> getListOrder(String search, String status, int page) {
        List<Order> orderItems = null;
        DBContext dBContext = new DBContext();
        StringBuilder sql = new StringBuilder("SELECT o.*, u.fullname, u.username FROM [dbo].[Order] o JOIN [dbo].[User] u\n"
                + "				 On u.userId = o.customerId WHERE 1=1");

        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND (o.orderId LIKE ? OR u.fullname LIKE ? OR u.username LIKE ?)");
        }

        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND o.status = ?");
        }

        sql.append(" ORDER BY o.orderDate DESC OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY");

        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql.toString());
            int paramIndex = 1;

            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search + "%";
                statement.setString(paramIndex++, searchPattern);
                statement.setString(paramIndex++, searchPattern);
                statement.setString(paramIndex++, searchPattern);
            }

            if (status != null && !status.trim().isEmpty()) {
                statement.setString(paramIndex++, status);
            }

            int offset = (page - 1) * 10;
            statement.setInt(paramIndex, offset);

            ResultSet rs = statement.executeQuery();
            orderItems = new ArrayList<>();

            while (rs.next()) {
                Order order = new Order();
              
                order.setOrderDate(rs.getString("orderDate"));
                order.setOrderId(rs.getInt("orderId"));
                order.setShipDate(rs.getString("shipDate"));
                order.setStatus(rs.getString("status"));
                order.setTotalPrice(rs.getFloat("totalPrice"));
                order.setToAddress(rs.getString("toAddress"));
                User user = new User();
                user.setUserId(rs.getInt("customerId"));
                user.setUserName(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));

                order.setUser(user);
                orderItems.add(order);
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
        return orderItems;
    }

     public boolean changeStatus(String value, String orderId) {
        boolean check = false;
        DBContext dBContext = new DBContext();
        String sql = "UPDATE [dbo].[Order] SET status = ? "
                + " WHERE orderId = ?";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setString(1, value);
            statement.setString(2, orderId);

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
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.getListOrder(null,"pending", 1));
    }
}
