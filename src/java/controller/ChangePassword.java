/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword"})
public class ChangePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("change-password.jsp").forward(request, response);
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
        String oldPasword = request.getParameter("oldPassword");
        String newPasword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        if (oldPasword != null && newPasword != null && confirmNewPassword != null && newPasword.equals(confirmNewPassword)) {
            AccountDAO accountDAO = new AccountDAO();
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("currentUser");
            User user = (User) obj;
            if (accountDAO.changePassword(user.getUserId(), oldPasword, newPasword)) {
                response.sendRedirect("profile?message=Change password successfully");

            } else {
                response.sendRedirect("profile?message=Change password fail");

            }
        } else {
            response.sendRedirect("profile?message=Fill all information");

        }

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
