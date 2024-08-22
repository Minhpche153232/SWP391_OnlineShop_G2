/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import models.CartItem;
import models.ProductDetailKey;
import models.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("currentUser");
        if (currentUser ==  null) {
            session.setAttribute("notificationErr", "You must login first!");
            response.sendRedirect("login");
            return;
        }
        Map<ProductDetailKey, CartItem> cart = (Map<ProductDetailKey, CartItem>) session.getAttribute("cart");

        double subtotal = 0.0;
        if (cart != null && !cart.isEmpty()) {
            for (CartItem item : cart.values()) {
                double price = item.getProductDetail().getProduct().getPrice();
                double discount = item.getProductDetail().getDiscount();
                double discountedPrice = price - (price * discount / 100);
                subtotal += item.getQuantity() * discountedPrice;
            }
        }

        request.setAttribute("subtotal", subtotal);
        request.setAttribute("cart", cart);

        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

    }

}
