/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO(); // Initialize your DAO here
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch form data
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("confirmpassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phonenumber");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");

        // Simple validation
        String errorMessage = null;
        if (!password.equals(repassword)) {
            errorMessage = "Passwords do not match!";
        } else if (userDAO.usernameExists(username)) {
            errorMessage = "Username already exists!";
        } else if (userDAO.emailExists(email)) {
            errorMessage = "Email already exists!";
        } else if (userDAO.phoneExists(phone)) {
            errorMessage = "Phone number already exists!";
        }


        if (errorMessage != null) {
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Create a User object
        User user = new User();
        user.setFullname(fullname);
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setDob(dobStr);  // Set Date object
        user.setAddress(address);
        if(gender.equals("Female")){
            user.setGender(false);
        }else{
            user.setGender(true);
        }
        user.setBalance(0);
        user.setRole("3");
        user.setStatus(true);
        
        
        
        

        // Save user to the database
        boolean userCreated = userDAO.createUser(user);

        if (userCreated) {
            response.sendRedirect("login.jsp");
        } else {
            errorMessage = "Failed to register the user. Please try again.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

}
