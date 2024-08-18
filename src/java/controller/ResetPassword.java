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

/**
 *
 * @author catmi
 */
public class ResetPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO daoU = new UserDAO();
        Validate val = new Validate();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String txtInput = request.getParameter("txtInput");
        Cookie currentCode = new Cookie("code", "");
        if (action.equals("checkUser")) {
            String email = daoU.getEmailForResetPW(txtInput);
            if (email == null) {
                request.setAttribute("txtInput", txtInput);
                request.setAttribute("errorMessage", "Email or Username is not exist");
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            } else {
                String code = val.RandomCode();
                currentCode.setValue(code);
                currentCode.setMaxAge(300);
                new Email().sendEmail(email, code);
                session.setAttribute("email", "*****" + email.substring(email.indexOf("@")-2));
                request.setAttribute("getEmail", "true");
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            }
        }else if(action.equals("checkCode")){
            String code = (String) session.getAttribute("code");
            String codeInput = request.getParameter("codeInput");
            if(!code.equals(codeInput)){
                request.setAttribute("errorMessage", "Email or Username is not exist");
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
