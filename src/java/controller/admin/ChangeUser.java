/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Admin
 */
public class ChangeUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object objUser = session.getAttribute("currentUser");
        User user = (User) objUser;
        if (user != null && user.getRole().equals("1")) {

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
                    response.sendRedirect("user-manager?message=Change successfully");
                } else {
                    response.sendRedirect("user-manager?message=Change fail");

                }
            } catch (Exception e) {
                response.sendRedirect("user-manager?message=Change fail" + e);
            }

        } else {
            response.sendRedirect("/online_shop/home");

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
