/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.OrderDAO;
import dao.TypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import models.Brand;
import models.Category;
import models.OrderItem;
import models.Type;
import models.User;

/**
 *
 * @author ASUS
 */
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        String userIdStr = request.getParameter("userId");
        String status = request.getParameter("status");
        if (status == null) {
            status = "pending";
        }
        int userId = 0;
        if (userIdStr == null) {
            HttpSession session = request.getSession();
            Object objUser = session.getAttribute("currentUser");
            if (objUser != null) {
                User user = (User) objUser;
                userId = user.getUserId();
            } else {
                response.sendRedirect("home");
            }
        } else {
            try {
                userId = Integer.parseInt(userIdStr);
            } catch (Exception e) {

                response.sendRedirect("home");
            }
        }
        OrderDAO orderDAO = new OrderDAO();
        List<OrderItem> listOrderDetail = orderDAO.getMyOrder(userId);
        List<OrderItem> listOrderDetail2 = new ArrayList<>();

        for (OrderItem orderItem : listOrderDetail) {
            if (orderItem.getOrder().getStatus().equals(status)) {
                listOrderDetail2.add(orderItem);
            }
        }
        request.setAttribute("status", status);
        request.setAttribute("listOrderDetail", listOrderDetail2);
        request.getRequestDispatcher("order.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
