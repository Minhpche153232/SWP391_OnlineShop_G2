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
import models.User;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                AccountDAO accountDAO = new AccountDAO();
                User user = accountDAO.login(userName, password);
                if(user != null){
                    HttpSession session = request.getSession();
                    session.setAttribute("currentUser", user);
                    response.sendRedirect("home");
                }else{
                    response.getWriter().print("login fail");
                }

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
