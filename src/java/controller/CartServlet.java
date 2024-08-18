/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.TypeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Brand;
import models.CartItem;
import models.Category;
import models.ProductDetailKey;
import models.Type;
import models.User;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        
        //get data filter
        CategoryDAO categoryDAO = new CategoryDAO();
        BrandDAO brandDAO = new BrandDAO();
        TypeDAO typeDAO = new TypeDAO();
        List<Category> categories = categoryDAO.getAllCategories();
        List<Brand> brands = brandDAO.getAllBrands();
        List<Type> types = typeDAO.getAllTypes();
        request.setAttribute("categories", categories);
        request.setAttribute("brands", brands);
        request.setAttribute("types", types);
        //

        if (user != null) {
            Map<ProductDetailKey, CartItem> cart = (Map<ProductDetailKey, CartItem>) session.getAttribute("cart");

            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            request.setAttribute("cart", cart);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            session.setAttribute("notificationErr", "You must login first!");
            response.sendRedirect("login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        Map<ProductDetailKey, CartItem> cart = (Map<ProductDetailKey, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        if ("update".equals(action)) {
            updateCartItem(request, cart);
            session.setAttribute("notification", "Update successfully!");
        } else if ("remove".equals(action)) {
            System.out.println("OKK");
            removeCartItem(request, cart);
            session.setAttribute("notification", "Remove successfully!");
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart"); // Redirect back to the cart page
    }

    private void updateCartItem(HttpServletRequest request, Map<ProductDetailKey, CartItem> cart) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int size = Integer.parseInt(request.getParameter("size"));
        String color = request.getParameter("color");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ProductDetailKey key = new ProductDetailKey(productId, size, color);

        if (cart.containsKey(key)) {
            if (quantity > 0) {
                cart.get(key).setQuantity(quantity);

            } else {
                cart.remove(key); // Remove the item if quantity is set to zero
            }
        }
    }

    private void removeCartItem(HttpServletRequest request, Map<ProductDetailKey, CartItem> cart) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int size = Integer.parseInt(request.getParameter("size"));
        String color = request.getParameter("color");

        ProductDetailKey key = new ProductDetailKey(productId, size, color);

        System.out.println("OK");
        if (cart.containsKey(key)) {
            System.out.println("OK");
            cart.remove(key);
        }
    }
}
