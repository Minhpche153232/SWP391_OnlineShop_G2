/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Product;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public Product getById(Integer productId) {
        Product p = null;
        try {
            String query = """
                       Select * from Product where productId = ?
                       """;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    p = new Product();
                    p.setProductId(rs.getInt("productId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
    public static void main(String[] args) {
        ProductDAO dAO = new ProductDAO();
        System.out.println(dAO.getById(1));
    }
}
