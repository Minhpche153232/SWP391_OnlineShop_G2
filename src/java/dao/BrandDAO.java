/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.*;
import java.sql.*;
import models.*;

/**
 *
 * @author ADMIN
 */
public class BrandDAO extends DBContext {

    public List<Brand> getAllBrands() {
        List<Brand> brands = new ArrayList<>();
        try {
            String query = "SELECT * FROM Brand";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandId(rs.getInt("brandId"));
                brand.setBrandName(rs.getString("brandName"));
                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }

    public Brand getBrandById(int brandId) {
        Brand brand = null;
        try {
            String query = "SELECT * FROM Brand WHERE brandId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, brandId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                brand = new Brand();
                brand.setBrandId(rs.getInt("brandId"));
                brand.setBrandName(rs.getString("brandName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brand;
    }

    public boolean checkBrandExist(String txtName) {
        Category c = null;
        try {
            String query = "SELECT * FROM Brand WHERE brandName = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, txtName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean AddNewBrand(Brand b) {
        try {
            String query = """
                           insert into Brand(brandName)
                           values(?)""";
            ps = conn.prepareStatement(query);
            ps.setString(1, b.getBrandName());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean UpdateBrand(Brand b) {
        try {
            String query = """
                           Update Brand set brandName = ?
                           where brandId = ?
                           """;
            ps = conn.prepareStatement(query);
            ps.setString(1, b.getBrandName());
            ps.setInt(2, b.getBrandId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
