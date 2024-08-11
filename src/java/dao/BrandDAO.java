/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.*;
import java.sql.*;
import models.Brand;
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
}
