/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import config.Validate;
import dao.AccountDAO;
import dao.UserDAO;
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
 * @author ACER
 */
public class ChangeMail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mess = request.getParameter("mess");
        request.setAttribute("mess", mess);
        request.getRequestDispatcher("verify-change-mail.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        response.setContentType("text/html");
        String type = request.getParameter("type");
        if (type != null && type.equals("change")) {
            String code = (String) session.getAttribute("code");
            String codeInput = request.getParameter("code");
            String mailVerify = (String) session.getAttribute("mailVerify");
            if (codeInput.equals(code)) {
                User user = (User) session.getAttribute("currentUser");

                AccountDAO accountDAO = new AccountDAO();
                if (user != null && accountDAO.changeEmailUser(mailVerify, user.getUserId())) {
                    user.setEmail(mailVerify);
                    session.setAttribute("currentUser", user);
                    response.sendRedirect("profile?message=Success! Your email is changed");
                }else{
                     response.sendRedirect("ChangeMail?mess=Not found user !");
                }
            } else {
                response.sendRedirect("ChangeMail?mess=Code invalidate !");
            }
        } else {
            String to = request.getParameter("newMail");

            UserDAO userDAO = new UserDAO();
            if (userDAO.emailExists(to)) {
                response.sendRedirect("profile?message=Changing email has been existed");
            } else {
                String subject = "Verify change mail";
                Validate val = new Validate();
                String code = val.RandomCode();
                session.setAttribute("code", code);
                session.setAttribute("mailVerify", to);

                String msg = "<div><div>Account with this mail has been registered. This code to verify: " + code
                        + "</div> <div style=\"margin-top: 20px\"><i>*** Not reply this mail ***</i></div></div>";
                Mailer.send(to, subject, msg);
                response.sendRedirect("ChangeMail?mess=To get code, check your email");
            }
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
