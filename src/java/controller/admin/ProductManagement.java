/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import models.*;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductManagement", urlPatterns = {"/admin/manage-product"})
public class ProductManagement extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchParam = request.getParameter("searchParam");
        Integer categoryId = request.getParameter("categoryId") != null && !request.getParameter("categoryId").isEmpty()
                ? Integer.valueOf(request.getParameter("categoryId")) : null;
        Integer brandId = request.getParameter("brandId") != null && !request.getParameter("brandId").isEmpty()
                ? Integer.valueOf(request.getParameter("brandId")) : null;
        Integer typeId = request.getParameter("typeId") != null && !request.getParameter("typeId").isEmpty()
                ? Integer.valueOf(request.getParameter("typeId")) : null;
        Boolean isActive = request.getParameter("isActive") != null && !request.getParameter("isActive").isEmpty()
                ? Boolean.valueOf(request.getParameter("isActive")) : null;
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int pageSize = 5;  // Number of items per page

        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAllProductWithParam(searchParam, categoryId, brandId,typeId, isActive);

        CategoryDAO categoryDAO = new CategoryDAO();
        BrandDAO brandDAO = new BrandDAO();
        List<Category> categories = categoryDAO.getAllCategories();
        List<Brand> brands = brandDAO.getAllBrands();

        // Pagination
        int totalProducts = products.size();
        int totalPages = (int) Math.ceil(totalProducts / (double) pageSize);
        List<Product> paginatedProducts = productDAO.Paging(products, page, pageSize);

        TypeDAO typeDAO = new TypeDAO();
        List<Type> types = typeDAO.getAllTypes();

        request.setAttribute("types", types);
        request.setAttribute("products", paginatedProducts);
        request.setAttribute("categories", categories);
        request.setAttribute("brands", brands);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("product-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        ProductDAO productDAO = new ProductDAO();
        switch (action) {
            case "add" -> {
                String productName = request.getParameter("productName");
                String productCode = request.getParameter("productCode");
                String description = request.getParameter("description");
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                int brandId = Integer.parseInt(request.getParameter("brandId"));
                int typeId = Integer.parseInt(request.getParameter("typeId"));
                float price = Float.parseFloat(request.getParameter("price"));
                boolean status = request.getParameter("status").equals("1");
                // Check if product name or code already exists
                if (productDAO.isProductNameExist(productName.trim())) {
                    session.setAttribute("notificationErr", "Product name already exists.");
                    response.sendRedirect(request.getContextPath() + "/admin/manage-product");
                    return;
                }
                if (productDAO.isProductCodeExist(productCode.trim())) {
                    session.setAttribute("notificationErr", "Product code already exists.");
                    response.sendRedirect(request.getContextPath() + "/admin/manage-product");
                    return;
                }
                Product newProduct = new Product();
                newProduct.setProductName(productName);
                newProduct.setProductCode(productCode);
                newProduct.setCategory(new CategoryDAO().getCategoryById(categoryId));
                newProduct.setBrand(new BrandDAO().getBrandById(brandId));
                newProduct.setType(new TypeDAO().getTypeById(typeId));
                newProduct.setPrice(price);
                newProduct.setActive(status);
                newProduct.setDescription(description);
                try {
                    productDAO.addProduct(newProduct);
                    session.setAttribute("notification", "Product added successfully!");
                } catch (Exception e) {
                    session.setAttribute("notificationErr", "Failed to add product.");
                }
            }
            case "edit" -> {
                int productId = Integer.parseInt(request.getParameter("productId"));
                String productName = request.getParameter("productName");
                String productCode = request.getParameter("productCode");
                String description = request.getParameter("description");
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                int brandId = Integer.parseInt(request.getParameter("brandId"));
                int typeId = Integer.parseInt(request.getParameter("typeId"));
                float price = Float.parseFloat(request.getParameter("price"));
                boolean status = "1".equals(request.getParameter("status"));

                // Check if the product name already exists (excluding current product)
                if (productDAO.isProductNameExist(productName, productId)) {
                    session.setAttribute("notificationErr", "Product name already exists.");
                    response.sendRedirect(request.getContextPath() + "/admin/manage-product");
                    return;
                }

                // Check if the product code already exists (excluding current product)
                if (productDAO.isProductCodeExist(productCode, productId)) {
                    session.setAttribute("notificationErr", "Product code already exists.");
                    response.sendRedirect(request.getContextPath() + "/admin/manage-product");
                    return;
                }

                Product product = new Product();
                product.setProductId(productId);
                product.setProductName(productName);
                product.setProductCode(productCode);
                product.setCategory(new CategoryDAO().getCategoryById(categoryId));
                product.setBrand(new BrandDAO().getBrandById(brandId));
                product.setType(new TypeDAO().getTypeById(typeId));
                product.setPrice(price);
                product.setActive(status);
                product.setDescription(description);

                try {
                    productDAO.updateProduct(product);
                    session.setAttribute("notification", "Product updated successfully!");
                } catch (Exception e) {
                    session.setAttribute("notificationErr", "Failed to update product.");
                }
            }
            case "deactivate" -> {
                int productId = Integer.parseInt(request.getParameter("productId"));
                productDAO.updateProductStatus(productId, false);
                session.setAttribute("notification", "Product deactivated successfully!");
            }
            case "activate" -> {
                int productId = Integer.parseInt(request.getParameter("productId"));
                productDAO.updateProductStatus(productId, true);
                session.setAttribute("notification", "Product activated successfully!");
            }
            default -> {
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/manage-product");
    }

}
