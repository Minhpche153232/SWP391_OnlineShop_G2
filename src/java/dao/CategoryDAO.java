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
                category.setDescription(rs.getString("description"));
                category.setStatus(rs.getBoolean("status"));
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
                category.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public Category checkCategoryExist(String txtName) {
        Category c = null;
        try {
            String query = "SELECT * FROM Category WHERE categoryName = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, txtName);
            rs = ps.executeQuery();
            while (rs.next()) {
                c.setCategoryId(rs.getInt("categoryId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getBoolean("status"));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean AddNewCategory(Category c) {
        try {
            String query = """
                           insert into Category(categoryName, [description])
                           values(?,?)""";
            ps = conn.prepareStatement(query);
            ps.setString(1, c.getCategoryName());
            ps.setString(2, c.getDescription());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean UpdateCategory(Category c) {
        try {
            String query = """
                           Update Category set categoryName = ?, [description] = ?
                           where categoryId = ?
                           """;
            ps = conn.prepareStatement(query);
            ps.setString(1, c.getCategoryName());
            ps.setString(2, c.getDescription());
            ps.setInt(3, c.getCategoryId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean DeleteCategory(Category c){
        try {
            String query = """
                           Update Category set status = ?
                           where categoryId = ?
                           """;
            ps = conn.prepareStatement(query);
            ps.setBoolean(1, c.isStatus());
            ps.setInt(2, c.getCategoryId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        Category c = new Category(3, "Forum", "Adidas Forum");
        if (dao.checkCategoryExist(c.getCategoryName()) == null) {
            boolean check = dao.UpdateCategory(c);
            System.out.println(check);
        } else {
            System.out.println("Loi");
        }
    }
}
