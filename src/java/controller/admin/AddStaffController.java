/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

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
 * @author ASUS
 */
public class AddStaffController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
HttpSession session = request.getSession();
        Object objUser = session.getAttribute("currentUser");
        User user = (User) objUser;
        if (user != null && user.getRole().equals("1")) {
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
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
        // Fetch form data
        UserDAO userDAO = new UserDAO();

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
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
            return;
        }

        // Create a User object
        User user = new User();
        user.setFullname(fullname);
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setDob(dob);  // Set Date object
        user.setAddress(address);
        if(gender.equals("Female")){
            user.setGender(false);
        }else{
            user.setGender(true);
        }
        user.setBalance(0);
        user.setRole("2");
        user.setStatus(true);
        
        
        
        

        // Save user to the database
        boolean userCreated = userDAO.createUser(user);

        if (userCreated) {
            response.sendRedirect("user-manager");
        } else {
            errorMessage = "Failed to register the user. Please try again.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
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
