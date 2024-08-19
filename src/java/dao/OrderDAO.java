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

    public List<OrderItem> getMyOrder(int userId) {
        List<OrderItem> orderItems = null;
        DBContext dBContext = new DBContext();
        String sql = "SELECT tb1.*, p.productName, p.price, (p.price * tb1.quantity) AS totalPrice FROM "
                + "(SELECT o.orderDate, o.shipAddress, o.shipDate, o.status, o.toAddress, o.totalPrice AS totalPriceOrder, "
                + "od.orderId, od.color, od.image, od.productId, od.quantity, od.size, o.customerId "
                + "FROM [dbo].[Order] o "
                + "JOIN [dbo].[OrderDetails] od ON o.orderId = od.orderId) AS tb1 "
                + "JOIN Product p ON p.productId = tb1.productId "
                + "WHERE tb1.customerId = ?";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setInt(1, userId);
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

    public List<Order> getListOrder() {
        List<Order> orderItems = null;
        DBContext dBContext = new DBContext();
        String sql = "sELECT tb2.*, u.fullname, u.username FROM (SELECT tb1.*, p.productName, p.price, (p.price * tb1.quantity) AS totalPrice FROM \n"
                + "                 (SELECT o.orderDate, o.shipAddress, o.shipDate, o.status, o.toAddress, o.totalPrice AS totalPriceOrder, \n"
                + "                 od.orderId, od.color, od.image, od.productId, od.quantity, od.size, o.customerId \n"
                + "                 FROM [dbo].[Order] o\n"
                + "                 JOIN [dbo].[OrderDetails] od ON o.orderId = od.orderId) AS tb1 \n"
                + "                 JOIN Product p ON p.productId = tb1.productId) AS tb2 JOIN [dbo].[User] u\n"
                + "				 On u.userId = tb2.customerId "
                + "";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
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
                User user = new User();
                user.setUserId(rs.getInt("customerId"));
                user.setUserName(rs.getString("userName"));
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

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.getListOrder());
    }
}
