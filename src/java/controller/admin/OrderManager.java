/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

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
import java.util.List;
import models.Brand;
import models.Category;
import models.Order;
import models.Type;
import models.User;

/**
 *
 * @author Admin
 */
public class OrderManager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object objUser = session.getAttribute("currentUser");
        User user = (User) objUser;
        if (user == null || user.getRole().equals("3")) {
            response.sendRedirect("/online_shop/home");
        } else {
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
            String message = request.getParameter("message");
            String userIdStr = request.getParameter("userId");
            String status = request.getParameter("status");
            String search = request.getParameter("search");
            String page = request.getParameter("page");
            int pageIndex = 1;
            if (status == null) {
                status = "pending";
            }

            OrderDAO orderDAO = new OrderDAO();
            List<Order> listOrderDetail = orderDAO.getListOrder(search, status, pageIndex);

            request.setAttribute("page", pageIndex);
            request.setAttribute("search", search);
            request.setAttribute("status", status);
            request.setAttribute("message", message);
            request.setAttribute("listOrderDetail", listOrderDetail);
            request.getRequestDispatcher("order-management.jsp").forward(request, response);
        }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
