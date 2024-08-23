/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.BrandDAO;
import dao.CategoryDAO;
import dao.TypeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.Brand;
import models.Category;
import models.Type;
import models.User;

/**
 *
 * @author Admin
 */
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        //get data filter
        CategoryDAO categoryDAO = new CategoryDAO();
        BrandDAO brandDAO = new BrandDAO();
        TypeDAO typeDAO = new TypeDAO();
        List<Category> categories = categoryDAO.getAllCategories();
        List<Brand> brands = brandDAO.getAllBrands();
        List<Type> types = typeDAO.getAllTypes();
        request.setAttribute("categories", categories);
        request.setAttribute("brands", brands);
        request.setAttribute("types", types);
        //
        HttpSession session = request.getSession();
        Object objectUser = session.getAttribute("currentUser");
        String message = request.getParameter("message");
        if (message != null) {
            request.setAttribute("message", message);
        }
        if (objectUser != null) {
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Object objectUser = session.getAttribute("currentUser");
        if (objectUser == null) {

            response.sendRedirect("home");
        }
        User userCurrent = (User) objectUser;
        String userName = request.getParameter("userName");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        String balanceStr = Float.valueOf(request.getParameter("balance")).toString().replaceAll("\\.?0*$", "");
        boolean gender = request.getParameter("gender").equals("male");
        String avatar = request.getParameter("avatar");
        float balance = Float.parseFloat(balanceStr);
        AccountDAO accountDAO = new AccountDAO();
        if (avatar != null) {
            avatar = "images/" + avatar;
        }
        userCurrent.setAvatar(avatar);
        userCurrent.setUserName(userName);
        userCurrent.setFullname(fullName);
        userCurrent.setEmail(email);
        userCurrent.setPhone(phone);
        userCurrent.setAddress(address);
        userCurrent.setDob(dob);
        userCurrent.setBalance(balance);
        userCurrent.setGender(gender);
        boolean checkUser = accountDAO.checkUser(userCurrent.getUserId(), userName);
        if (!checkUser) {

            boolean check = accountDAO.changeProfile(userCurrent);

            if (check) {
                session.setAttribute("currentUser", userCurrent);
                response.sendRedirect("profile?message=Change profile successfully");

            } else {
                response.sendRedirect("profile?message=Change profile fail");

            }
        } else {
            response.sendRedirect("profile?message=User name already exists");

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
