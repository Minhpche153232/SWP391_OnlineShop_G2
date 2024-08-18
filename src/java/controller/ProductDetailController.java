/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Product;
import models.ProductDetail;
import models.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductDetailController", urlPatterns = {"/product-detail"})
public class ProductDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        ProductDAO pdao = new ProductDAO();
        Product product = pdao.getById(productId);
        HttpSession session = request.getSession();
        request.setAttribute("product", product);
        User currentUser = (User) session.getAttribute("currentUser");
        request.setAttribute("currentUser", currentUser);
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        String image;
        int discount;
        if (size != null && color != null) {
            int intSize = Integer.parseInt(size);
            ProductDetail pd = pdao.getProductDetailBySizeAndColor(productId, intSize, color);
            image = pd.getImage();
            discount = pd.getDiscount();
            request.setAttribute("image", image);
            request.setAttribute("discount", discount);
        } else {
            if (!product.getProductDetails().isEmpty()) {
                image = product.getProductDetails().get(0).getImage();
                request.setAttribute("image", image);
            }
        }
        request.getRequestDispatcher("product-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
