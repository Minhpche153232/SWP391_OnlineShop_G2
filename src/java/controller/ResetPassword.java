/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import config.Email;
import config.Validate;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author catmi
 */
public class ResetPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("reset-password.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO daoU = new UserDAO();
        Validate val = new Validate();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String txtInput = request.getParameter("txtInput");
        HttpSession currentCode = request.getSession();
        
        if (action.equals("checkUser")) {
            String email = daoU.getEmailForResetPW(txtInput);
            if (email == null) {
                request.setAttribute("txtInput", txtInput);
                request.setAttribute("errorMessage", "Email or Username is not exist");
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            } else {
                String code = val.RandomCode();
                currentCode.setAttribute("code", code);
                currentCode.setMaxInactiveInterval(300);
                new Email().sendEmail(email, code);
                session.setAttribute("email", email);
                session.setAttribute("emailView", "*****" + email.substring(email.indexOf("@") - 2));
                request.setAttribute("getEmail", "true");
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            }
        } else if (action.equals("checkCode")) {
            String btnSubmit = request.getParameter("btnSubmit");
            if (btnSubmit.equals("resend")) {
                String email = (String) session.getAttribute("email");
                String code = val.RandomCode();
                currentCode.setAttribute("code", code);
                currentCode.setMaxInactiveInterval(300);
                new Email().sendEmail(email, code);
                request.setAttribute("getEmail", "true");
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            } else {
                String code = (String) currentCode.getAttribute("code");
                String codeInput = request.getParameter("codeInput");
                if (code == null || code.isEmpty()) {
                    request.setAttribute("getEmail", "true");
                    request.setAttribute("errorMessage", "Code is empty");
                    response.sendRedirect("reset-password.jsp");
                } else if (!code.equals(codeInput)) {
                    request.setAttribute("getEmail", "true");
                    request.setAttribute("errorMessage", "Code is not correct");
                    request.getRequestDispatcher("reset-password.jsp").forward(request, response);
                } else {
                    currentCode.removeAttribute("code");
                    session.removeAttribute("emailView");
                    response.sendRedirect("new-password.jsp");
                }
            }

        } else if (action.equals("updatePass")) {
            String newpass = request.getParameter("newpass").trim();
            String confirmPass = request.getParameter("confirmPass").trim();
            if(newpass == null && confirmPass == null || newpass.isBlank() && confirmPass.isBlank()){
                request.setAttribute("errorMessage", "Please input password");
                request.getRequestDispatcher("new-password.jsp").forward(request, response);
            }
            else if (newpass.length() < 8 || newpass.contains(" ")) {
                request.setAttribute("newpass",newpass);
                request.setAttribute("confirmPass",confirmPass);
                request.setAttribute("errorMessage", "Password is from 8-15 characters and not contain space");
                request.getRequestDispatcher("new-password.jsp").forward(request, response);
            }else if(!confirmPass.equals(newpass)){
                request.setAttribute("errorMessage", "Confirm password isn't equal new password");
                request.getRequestDispatcher("new-password.jsp").forward(request, response);
            }else{
                String email = (String) session.getAttribute("email");
                UserDAO dao = new UserDAO();
                dao.updatePassword(newpass, email);
                session.removeAttribute("email");
                response.sendRedirect("login");
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
