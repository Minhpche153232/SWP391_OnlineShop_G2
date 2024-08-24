/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Order;
import models.OrderItem;
import models.Product;
import models.ProductDetail;
import models.User;

/**
 *
 * @author ASUS
 */
public class OrderDAO {

    public List<ProductDetail> getProductDetailMyOrder(int orderId) {
        List<ProductDetail> productDetail = null;
        DBContext dBContext = new DBContext();
        StringBuilder sql = new StringBuilder("SELECT DISTINCT pd.*, od.orderId, od.quantity FROM OrderDetails od join ProductDetails pd \n"
                + "ON pd.productId = od.productId WHERE od.orderId = ?");

        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql.toString());
            statement.setInt(1, orderId);

            // Set giá trị cho các tham số tìm kiếm nếu search không rỗng hoặc null
            ResultSet rs = statement.executeQuery();
            productDetail = new ArrayList<>();

            while (rs.next()) {
                ProductDetail product = new ProductDetail();
                product.setProductId(rs.getInt("productId"));
                product.setImage(rs.getString("image"));
                product.setColor(rs.getString("color"));
                product.setSize(rs.getInt("size"));
                product.setUnitInStock(rs.getInt("unitInStock"));

                productDetail.add(product);
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
        return productDetail;
    }

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
                product.setImage(rs.getString("image"));
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

    public List<OrderItem> getMyOrder(int orderId) {
        List<OrderItem> orderItems = null;
        DBContext dBContext = new DBContext();
        StringBuilder sql = new StringBuilder("SELECT tb1.*, p.productName, p.price, (p.price * tb1.quantity) AS totalPrice FROM "
                + "(SELECT o.orderDate, o.shipAddress, o.shipDate, o.status, o.toAddress, o.totalPrice AS totalPriceOrder, "
                + "od.orderId, od.color, od.image, od.productId, od.quantity, od.size, o.customerId "
                + "FROM [dbo].[Order] o "
                + "JOIN [dbo].[OrderDetails] od ON o.orderId = od.orderId) AS tb1 "
                + "JOIN Product p ON p.productId = tb1.productId "
                + "WHERE tb1.orderId = ?");

        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql.toString());
            statement.setInt(1, orderId);

            // Set giá trị cho các tham số tìm kiếm nếu search không rỗng hoặc null
            ResultSet rs = statement.executeQuery();
            orderItems = new ArrayList<>();

            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                Product product = new Product();
                Order order = new Order();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getFloat("price"));
                product.setImage(rs.getString("image"));
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

    public boolean changeQuantityProduct(int quantity, int productId, String color, String size) {
        boolean check = false;
        DBContext dBContext = new DBContext();
        String sql = "UPDATE [dbo].[ProductDetails] SET unitInStock = ? "
                + " WHERE productId = ? "
                + " AND color = ? "
                + " AND size = ?";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, productId);
            statement.setString(3, color);
            statement.setString(4, size);
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

    public boolean saveOrder(Order order, List<OrderItem> orderItems) {
        DBContext dBContext = new DBContext();
        try {
            // Start a transaction
            dBContext.conn.setAutoCommit(false);

            // Insert into Order table
            String orderSql = "INSERT INTO [Order] (customerId, totalPrice, orderDate, shipAddress, toAddress, staffId, status) VALUES (?, ?, GETDATE(),?, ?, ?, ?)";
            PreparedStatement orderStmt = dBContext.conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, order.getUser().getUserId());
            orderStmt.setFloat(2, order.getTotalPrice());
            orderStmt.setString(3, "HN");
            orderStmt.setString(4, order.getToAddress());
            orderStmt.setInt(5, 1);
            orderStmt.setString(6, order.getStatus());
            int affectedRows = orderStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Failed to create order.");
            }

            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                order.setOrderId(orderId);
            } else {
                throw new SQLException("Failed to obtain order ID.");
            }

            // Insert into OrderItems table
            String orderItemSql = "INSERT INTO OrderDetails (orderId, productId, quantity, size, color, image) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement orderItemStmt = dBContext.conn.prepareStatement(orderItemSql);

            for (OrderItem orderItem : orderItems) {
                orderItemStmt.setInt(1, order.getOrderId());
                orderItemStmt.setInt(2, orderItem.getProduct().getProductId());
                orderItemStmt.setInt(3, orderItem.getQuantity());
                orderItemStmt.setInt(4, orderItem.getSize());
                orderItemStmt.setString(5, orderItem.getColor());
                orderItemStmt.setString(6, orderItem.getImage());
                orderItemStmt.addBatch();
            }

            orderItemStmt.executeBatch();

            // Commit the transaction
            dBContext.conn.commit();
            return true;
        } catch (SQLException ex) {
            try {
                dBContext.conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
            return false;
        } finally {
            try {
                dBContext.conn.setAutoCommit(true);
                dBContext.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.getMyOrder(1));
    }
}
