package controller;

import dao.OrderDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.CartItem;
import models.Order;
import models.OrderItem;
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
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
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
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            session.setAttribute("notificationErr", "You must login first!");
            response.sendRedirect("login");
            return;
        }

        Map<ProductDetailKey, CartItem> cart = (Map<ProductDetailKey, CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            session.setAttribute("notificationErr", "Your cart is empty.");
            response.sendRedirect("checkout");
            return;
        }
        String address = request.getParameter("address");
        double subtotal = Double.parseDouble(request.getParameter("subtotal"));

        // Create Order object
        Order order = new Order();
        order.setUser(currentUser);
        order.setTotalPrice((float) subtotal);
        order.setToAddress(address);
        order.setStatus("pending");
        order.setShipDate(null);

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProductDetail().getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSize(cartItem.getProductDetail().getSize());
            orderItem.setColor(cartItem.getProductDetail().getColor());
            orderItem.setImage(cartItem.getProductDetail().getImage());
            orderItem.setTotalPrice((float) (cartItem.getQuantity() * (cartItem.getProductDetail().getProduct().getPrice() - cartItem.getProductDetail().getProduct().getPrice() * cartItem.getProductDetail().getDiscount() / 100)));
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        if (currentUser.getBalance() < subtotal) {
            // Insufficient balance
            session.setAttribute("notificationErr", "Your balance is not enough to place the order.");
            response.sendRedirect("checkout");
            return;
        } else {

            Double newBalance = currentUser.getBalance() - subtotal;
            currentUser.setBalance(newBalance.floatValue());

            // Update user balance in the database
            UserDAO userDAO = new UserDAO();
            boolean isUpdated = userDAO.updateUserBalance(currentUser);
            if (isUpdated) {

                // Save the order using OrderDAO
                OrderDAO orderDAO = new OrderDAO();
                boolean success = orderDAO.saveOrder(order, orderItems);

                if (success) {
                    session.setAttribute("notification", "Order placed successfully!");
                    session.removeAttribute("cart");
                } else {
                    session.setAttribute("notificationErr", "Failed to place order. Please try again.");
                }
            }

        }

        response.sendRedirect("thank-for-order");
    }
}
