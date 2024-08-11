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
import models.Product;
import models.ProductDetail;
import models.User;

/**
 *
 * @author Admin
 */
public class ProductDAO {

    public List<ProductDetail> getTopCheapestProduct() {
        DBContext dBContext = new DBContext();
        List<ProductDetail> list = null;
        String sql = "SELECT TOP (3) p.*, pd.color, pd.size\n"
                + "FROM [OnlineShop_SWP391].[dbo].[Product] p\n"
                + "JOIN [OnlineShop_SWP391].[dbo].[ProductDetails] pd \n"
                + "ON p.productId = pd.productId\n"
                + "ORDER BY p.price ASC;";
        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getFloat("price"));
                ProductDetail productDetail = new ProductDetail();
                productDetail.setProduct(p);
                productDetail.setColor(rs.getString("color"));
                productDetail.setSize(rs.getInt("size"));
                list.add(productDetail);
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
        return list;
    }
    public static void main(String[] args) {
        ProductDAO dAO = new ProductDAO();
        System.out.println(dAO.getTopCheapestProduct());
    }

}
