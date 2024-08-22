/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import config.Validate;
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

/**
 *
 * @author ACER
 */
public class SendMail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        UserDAO userDAO = new UserDAO();
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        Validate val = new Validate();
        String code = val.RandomCode();
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        session.setAttribute("mailVerify", to);

        String msg = "<div><div>Account with this mail has been registered. This code to verify: " + code
                + "</div> <div style=\"margin-top: 20px\"><i>*** Not reply this mail ***</i></div></div>";
        Mailer.send(to, subject, msg);
        response.sendRedirect("active-account?mess=To get code, check your email");
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
