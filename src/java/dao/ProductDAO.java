
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.Timestamp;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Product;
import models.ProductDetail;
import dao.DBContext;

import java.util.List;
import models.Brand;
import models.Category;

import models.ProductDetail;
import models.Type;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public List<ProductDetail> getTopCheapestProduct(Integer[] rangePrice, String search, Integer size, String color, Integer typeId, Integer categoryId, Integer brandId) {
        DBContext dBContext = new DBContext();
        List<ProductDetail> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT p.*, pd.color, pd.size, pd.image as imagePD FROM [OnlineShop_SWP391].[dbo].[Product] p JOIN [OnlineShop_SWP391].[dbo].[ProductDetails] pd ON p.productId = pd.productId WHERE 1=1");

        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND p.productName LIKE ?");
        }
        if (size != null) {
            sql.append(" AND pd.size = ?");
        }
        if (color != null) {
            sql.append(" AND pd.color = ?");
        }
        if (typeId != null) {
            sql.append(" AND p.typeId = ?");
        }
        if (categoryId != null) {
            sql.append(" AND p.categoryId = ?");
        }
        if (brandId != null) {
            sql.append(" AND p.brandId = ?");
        }
        if (rangePrice != null && rangePrice[0] != null) {
            if (rangePrice.length > 1 && rangePrice[1] != null) {
                sql.append(" AND p.price BETWEEN ? AND ?");
            } else {
                sql.append(" AND p.price >= ?");
            }
        }

        sql.append(" ORDER BY p.price ASC");

        try {
            PreparedStatement statement = dBContext.conn.prepareStatement(sql.toString());
            int paramIndex = 1;

            if (search != null && !search.trim().isEmpty()) {
                statement.setString(paramIndex++, "%" + search + "%");
            }
            if (size != null) {
                statement.setInt(paramIndex++, size);
            }
            if (color != null) {
                statement.setString(paramIndex++, color);
            }
            if (typeId != null) {
                statement.setInt(paramIndex++, typeId);
            }
            if (categoryId != null) {
                statement.setInt(paramIndex++, categoryId);
            }
            if (brandId != null) {
                statement.setInt(paramIndex++, brandId);
            }
            if (rangePrice != null && rangePrice[0] != null) {
                statement.setInt(paramIndex++, rangePrice[0]);
                if (rangePrice.length > 1 && rangePrice[1] != null) {
                    statement.setInt(paramIndex++, rangePrice[1]);
                }
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("imagePD"));
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
            if (dBContext.conn != null) {
                try {
                    dBContext.conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public List<Product> getAllProductWithParam(String searchParam, Integer categoryId, Integer brandId, Integer typeId, Boolean isActive) {
        List<Product> products = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                SELECT 
                    p.*, 
                    c.categoryName, 
                    b.brandName, 
                    t.typeName 
                FROM 
                    Product p
                LEFT JOIN 
                    Category c ON p.categoryId = c.categoryId
                LEFT JOIN 
                    Brand b ON p.brandId = b.brandId
                LEFT JOIN 
                    Type t ON p.typeId = t.typeId
                WHERE 1 = 1
            """);

            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append(" AND p.productName LIKE ? ");
                params.add("%" + searchParam + "%");
            }
            if (categoryId != null) {
                query.append(" AND p.categoryId = ? ");
                params.add(categoryId);
            }
            if (typeId != null) {
                query.append(" AND p.typeId = ? ");
                params.add(typeId);
            }
            if (brandId != null) {
                query.append(" AND p.brandId = ? ");
                params.add(brandId);
            }
            if (isActive != null) {
                query.append(" AND p.status = ? ");
                params.add(isActive ? 1 : 0);
            }

            PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
            mapParams(preparedStatement, params);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("productId"));
                    product.setProductName(rs.getString("productName"));
                    product.setProductCode(rs.getString("productCode"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getFloat("price"));

                    Category category = new Category();
                    category.setCategoryId(rs.getInt("categoryId"));
                    category.setCategoryName(rs.getString("categoryName"));
                    product.setCategory(category);

                    Brand brand = new Brand();
                    brand.setBrandId(rs.getInt("brandId"));
                    brand.setBrandName(rs.getString("brandName"));
                    product.setBrand(brand);

                    Type type = new Type();
                    type.setTypeId(rs.getInt("typeId"));
                    type.setTypeName(rs.getString("typeName"));
                    product.setType(type);

                    product.setActive(rs.getBoolean("status"));

                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getById(Integer productId) {
        Product p = null;
        BrandDAO bdao = new BrandDAO();
        TypeDAO tdao = new TypeDAO();
        CategoryDAO cdao = new CategoryDAO();
        try {
            String query = "SELECT * FROM Product WHERE productId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setProductCode(rs.getString("productCode"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));

                // Set category, brand, type, and active status
                p.setCategory(cdao.getCategoryById(rs.getInt("categoryId")));
                p.setBrand(bdao.getBrandById(rs.getInt("brandId")));
                p.setActive(rs.getBoolean("status"));
                p.setType(tdao.getTypeById(rs.getInt("typeId")));

                // Fetch ProductDetails
                List<ProductDetail> details = getProductDetailsByProductId(productId);
                p.setProductDetails(details);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    private List<ProductDetail> getProductDetailsByProductId(int productId) {
        List<ProductDetail> details = new ArrayList<>();
        try {
            String query = "SELECT * FROM ProductDetails WHERE productId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ProductDetail detail = new ProductDetail();
                detail.setProductId(rs.getInt("productId"));
                detail.setSize(rs.getInt("size"));
                detail.setColor(rs.getString("color"));
                detail.setUnitInStock(rs.getInt("unitInStock"));
                detail.setImage(rs.getString("image"));
                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    public void addProduct(Product product) {
        try {
            if (product.getType() != null) {
                String query = "INSERT INTO Product (productName, productCode, categoryId, brandId,typeId, price, status, description) VALUES (?, ?, ?, ?, ?, ?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setString(2, product.getProductCode());
                preparedStatement.setInt(3, product.getCategory().getCategoryId());
                preparedStatement.setInt(4, product.getBrand().getBrandId());
                preparedStatement.setInt(5, product.getType().getTypeId());
                preparedStatement.setFloat(6, product.getPrice());
                preparedStatement.setBoolean(7, product.isActive());
                preparedStatement.setString(8, product.getDescription());
                preparedStatement.executeUpdate();
            } else {
                String query = "INSERT INTO Product (productName, productCode, categoryId, brandId, price, status, description) VALUES (?, ?, ?, ?, ?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setString(2, product.getProductCode());
                preparedStatement.setInt(3, product.getCategory().getCategoryId());
                preparedStatement.setInt(4, product.getBrand().getBrandId());
                preparedStatement.setFloat(5, product.getPrice());
                preparedStatement.setBoolean(6, product.isActive());
                preparedStatement.setString(7, product.getDescription());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isProductNameExist(String productName) {
        boolean exists = false;
        try {
            String query = "SELECT COUNT(*) FROM Product WHERE productName = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, productName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean isProductCodeExist(String productCode) {
        boolean exists = false;
        try {
            String query = "SELECT COUNT(*) FROM Product WHERE productCode = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, productCode);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void updateProductStatus(int productId, boolean status) {
        try {
            String query = "UPDATE Product SET status = ? WHERE productId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try {
            String query = "UPDATE Product SET productName = ?, productCode = ?, categoryId = ?, brandId = ?, typeId = ?, price = ?, status = ?, description = ? WHERE productId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductCode());
            preparedStatement.setInt(3, product.getCategory().getCategoryId());
            preparedStatement.setInt(4, product.getBrand().getBrandId());
            preparedStatement.setInt(5, product.getType().getTypeId());
            preparedStatement.setFloat(6, product.getPrice());
            preparedStatement.setBoolean(7, product.isActive());
            preparedStatement.setString(8, product.getDescription());
            preparedStatement.setInt(9, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isProductNameExist(String productName, int productId) {
        try {
            String query = "SELECT COUNT(*) FROM Product WHERE productName = ? AND productId != ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, productName);
            preparedStatement.setInt(2, productId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isProductCodeExist(String productCode, int productId) {
        try {
            String query = "SELECT COUNT(*) FROM Product WHERE productCode = ? AND productId != ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, productCode);
            preparedStatement.setInt(2, productId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ProductDetail getProductDetailBySizeAndColor(int productId, int size, String color) {
        ProductDetail detail = null;
        try {
            String query = "SELECT * FROM ProductDetails WHERE productId = ? AND size = ? AND color = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, size);
            ps.setString(3, color);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                detail = new ProductDetail(
                        rs.getInt("productId"),
                        rs.getInt("size"),
                        rs.getString("color"),
                        rs.getInt("unitInStock"),
                        rs.getString("image")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detail;
    }

    public void addProductDetail(ProductDetail detail) {
        try {
            String query = "INSERT INTO ProductDetails (productId, size, color, unitInStock, image) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, detail.getProductId());
            ps.setInt(2, detail.getSize());
            ps.setString(3, detail.getColor());
            ps.setInt(4, detail.getUnitInStock());
            ps.setString(5, detail.getImage());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProductDetail(ProductDetail detail) {
        try {
            String query = "UPDATE ProductDetails SET unitInStock = ? ,  image = ? WHERE productId = ? AND size = ? AND color = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, detail.getUnitInStock());
            ps.setString(2, detail.getImage());
            ps.setInt(3, detail.getProductId());
            ps.setInt(4, detail.getSize());
            ps.setString(5, detail.getColor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductDetail(int productId, int size, String color) {
        try {
            String query = "DELETE FROM ProductDetails WHERE productId = ? AND size = ? AND color = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, size);
            ps.setString(3, color);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mapParams(PreparedStatement ps, List<Object> args) throws SQLException {
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Float) {
                ps.setFloat(i++, (Float) arg);
            } else {
                ps.setString(i++, (String) arg);
            }

        }
    }

    public List<Product> Paging(List<Product> products, int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, products.size());

        if (fromIndex > toIndex) {
            fromIndex = toIndex;
        }

        return products.subList(fromIndex, toIndex);
    }

    public static void main(String[] args) {
        ProductDAO dAO = new ProductDAO();
//        String query = "INSERT INTO Product (productName, productCode, categoryId, brandId,typeId, price, status, description) VALUES (?, ?, ?, ?, ?, ?,?,?)";
//        Product d = new Product();
//        d.setActive(true);
//        Brand b = new Brand();
//        b.setBrandId(1);
//        Category c = new Category();
//        c.setCategoryId(1);
//        d.setPrice(12);
//        d.setDescription("dess");
//        d.setProductCode("code");
//        d.setProductName("name");
//        d.setBrand(b);
//        d.setCategory(c);
//        dAO.addProduct(d);
        List<ProductDetail> list = dAO.getTopCheapestProduct(null, "", null, null, null, null, null);
        System.out.println(list);
    }

}
