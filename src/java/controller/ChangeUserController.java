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

/**
 *
 * @author Admin
 */
public class ChangeUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String value = request.getParameter("value");
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        AccountDAO accountDAO = new AccountDAO();
        try {
            boolean check = false;
            if (type.equals("role")) {
                check = accountDAO.changeRoleUser(Integer.parseInt(value), Integer.parseInt(userId));
            } else {
                check = accountDAO.changeStatusUser(value.equals("true"), Integer.parseInt(userId));
            }
            if (check) {
                response.sendRedirect("dashboard?message=Submit successfully");
            } else {
                response.sendRedirect("dashboard?message=Submit fail");

            }
        } catch (Exception e) {
            response.sendRedirect("dashboard?message=Submit fail" + e);
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
