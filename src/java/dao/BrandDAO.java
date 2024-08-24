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
                brand.setStatus(rs.getBoolean("status"));
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

    public Brand checkBrandExist(String txtName) {
        Brand b = new Brand();
        try {
            String query = "SELECT * FROM Brand WHERE brandName = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, txtName);
            rs = ps.executeQuery();
            while (rs.next()) {
                b.setBrandId(rs.getInt("brandId"));
                b.setBrandName(rs.getString("brandName"));
                b.setStatus(rs.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
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
    
    public List<Brand> searchBrandByName(String[] listSearch){
        List<Brand> list = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM Brand");
            if(listSearch != null && listSearch.length > 0){
                query.append(" where ");
                for(int i = 0; i < listSearch.length; i++){
                    query.append("brandName like ? ");
                    if(i < listSearch.length - 1){
                        query.append(" or ");
                    }
                }
                ps = conn.prepareStatement(query.toString());
                for (int i = 0; i < listSearch.length; i++) {
                    ps.setString(i+1, "%"+listSearch[i]+"%");
                }
            }
            rs = ps.executeQuery();
            while(rs.next()){
                Brand brand = new Brand();
                brand.setBrandId(rs.getInt("brandId"));
                brand.setBrandName(rs.getString("brandName"));
                brand.setStatus(rs.getBoolean("status"));
                list.add(brand);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean DeleteBrand(Brand b){
        try {
            String query = """
                           Update Brand set status = ?
                           where brandId = ?
                           """;
            ps = conn.prepareStatement(query);
            ps.setBoolean(1, b.isStatus());
            ps.setInt(2, b.getBrandId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        BrandDAO dao = new BrandDAO();
        String txtSearch = "Nike Adidas Puma";
        String[] listSearch = txtSearch.split(" ");
        List<Brand> list = dao.searchBrandByName(listSearch);
        for (Brand b : list) {
            System.out.println(b.toString());
        }
    }
}
