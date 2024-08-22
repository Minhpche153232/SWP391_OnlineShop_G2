/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import models.User;

/**
 *
 * @author ACER
 */
public class ActiveAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mess = request.getParameter("mess");
        request.setAttribute("mess", mess);
        request.getRequestDispatcher("verify-mail.jsp").forward(request, response);

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
        String codeInput = request.getParameter("code");

        if (codeInput != null) {
            UserDAO userDAO = new UserDAO();
            HttpSession session = request.getSession();
            String code = (String) session.getAttribute("code");
            String mailVerify = (String) session.getAttribute("mailVerify");

            User user = userDAO.getUserByMailAndPassword(mailVerify);
            if (user == null) {
                request.getRequestDispatcher("verify-mail-fail.jsp").forward(request, response);
            } else {
                if (code.equals(codeInput)) {
                    AccountDAO accountDAO = new AccountDAO();
                    session.setAttribute("code", null);
                    session.setAttribute("mailVerify", null);

                    if (accountDAO.changeStatusUser(true, user.getUserId())) {
                        response.sendRedirect("login");

                    } else {
                        response.getWriter().print("Some thing error ! change fail");
                    }
                } else {
                    request.setAttribute("mess", "Code Invalidate");
                    request.getRequestDispatcher("verify-mail.jsp").forward(request, response);

                }
            }
        } else {
            request.getRequestDispatcher("verify-mail-fail.jsp").forward(request, response);

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
