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
        String sql = "SELECT tb1.*, p.productName, p.price FROM (SELECT o.orderDate, o.shipAddress, o.shipDate,  o.status, o.toAddress,o.totalPrice as totalPriceOrder,od.orderId, od.color, od.image, od.productId, od.quantity, od.size, o.customerId, o.totalPrice FROM [dbo].[Order] o JOIN [dbo].[OrderDetails] od \n"
                + "On  o.orderId = od.orderId) AS tb1 JOIN Product p \n"
                + "On p.productId = tb1.productId\n"
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

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.getMyOrder(1));
    }
}
