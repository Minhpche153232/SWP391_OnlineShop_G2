/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.*;
import java.sql.*;
import models.Category;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO extends DBContext {

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            String query = "SELECT * FROM Category";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    public Category getCategoryById(int categoryId) {
    Category category = null;
    try {
        String query = "SELECT * FROM Category WHERE categoryId = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, categoryId);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            category = new Category();
            category.setCategoryId(rs.getInt("categoryId"));
            category.setCategoryName(rs.getString("categoryName"));
            category.setDescription(rs.getString("description"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return category;
}

}
