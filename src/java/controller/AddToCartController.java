/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import models.CartItem;
import models.ProductDetail;
import models.ProductDetailKey;
import models.User;

@WebServlet(name = "AddToCartController", urlPatterns = {"/add-cart"})
public class AddToCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int size = Integer.parseInt(request.getParameter("size"));
        String color = request.getParameter("color");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        ProductDAO productDAO = new ProductDAO();
        ProductDetail productDetail = productDAO.getProductDetailBySizeAndColor(productId, size, color);
        if (user != null) {
            if (productDetail != null) {

                Map<ProductDetailKey, CartItem> cart = (Map<ProductDetailKey, CartItem>) session.getAttribute("cart");

                if (cart == null) {
                    cart = new HashMap<>();
                }

                ProductDetailKey key = new ProductDetailKey(productId, size, color);

                if (cart.containsKey(key)) {
                    CartItem existingItem = cart.get(key);
                    existingItem.setQuantity(existingItem.getQuantity() + 1);
                    session.setAttribute("cart", cart);
                } else {
                    cart.put(key, new CartItem(productDetail, 1));
                    session.setAttribute("cart", cart);
                }

            }
            session.setAttribute("notification", "Product successfully add to cart");
            response.sendRedirect("product-detail?id="+productId+"&color="+color+"&size="+size);
        } else {
            session.setAttribute("notification", "User must login first!");
            response.sendRedirect("login");
        }

    }
}
