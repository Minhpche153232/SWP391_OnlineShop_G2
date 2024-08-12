/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author Admin
 */
public class DashBoardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object objUser = session.getAttribute("currentUser");
        User user = (User) objUser;
        if (user != null && user.getRole().equals("1")) {
            String message = request.getParameter("message");

            String textSearch = request.getParameter("textSearch");
            String roleSearch = request.getParameter("roleSearch");
                        String activeSearch = request.getParameter("activeSearch");


            String pageIndexString = request.getParameter("pageIndex");
            int pageIndex = 1;
            if (textSearch == null) {
                textSearch = "";
            }
            if (pageIndexString != null) {
                try {
                    pageIndex = Integer.parseInt(pageIndexString);
                } catch (Exception e) {
                    response.getWriter().print("lỗi parse int");
                }
            }
            AccountDAO accountDAO = new AccountDAO();
            List<Role> listRole = accountDAO.getAllRole();

            int totalPage = accountDAO.countTotalPage(textSearch);
            List<User> listUser = accountDAO.searchUser(textSearch, pageIndex);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("listUser", listUser);
            request.setAttribute("listRole", listRole);
            request.setAttribute("message", message);
            request.setAttribute("pageIndex", pageIndex);
            if (roleSearch != null) {
                request.setAttribute("roleSearch", roleSearch);

            }
              if (activeSearch != null) {
                request.setAttribute("activeSearch", activeSearch);

            }

            request.getRequestDispatcher("user-management.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
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
