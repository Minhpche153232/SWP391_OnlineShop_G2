/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.*;

/**
 *
 * @author catmi
 */
public class CategoryManage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO dao = new CategoryDAO();
        String service = request.getParameter("service");
        HttpSession sess = request.getSession();
        User user = (User) sess.getAttribute("currentUser");
        if (user != null && user.getRole().equals("1") || user != null && user.getRole().equals("2")) {
            if (service == null) {
                List<Category> list = dao.getAllCategories();
                request.setAttribute("listCate", list);
                request.getRequestDispatcher("category-management.jsp").forward(request, response);
            } else if ("delete".equals(service)) {
                int id = Integer.parseInt(request.getParameter("cateId"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                Category c = new Category();
                c.setCategoryId(id);
                if (status == true) {
                    c.setStatus(false);
                } else {
                    c.setStatus(true);
                }
                dao.DeleteCategory(c);
                response.sendRedirect("category");
            }
        } else {
            response.sendRedirect("../home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO dao = new CategoryDAO();
        String service = request.getParameter("service");
        HttpSession sess = request.getSession();
        if ("update".equals(service)) {
            int id = Integer.parseInt(request.getParameter("categoryId"));
            String name = request.getParameter("categoryName").trim().replace("\\s+", " ");
            String description = request.getParameter("categoryDescription").trim().replace("\\s+", " ");
            Category c = new Category(id, name, description);
            Category exC = dao.checkCategoryExist(name.trim());
            if (name.isEmpty() || name == null
                    || description.isEmpty() || description == null
                    || name.equals(" ") || description.equals(" ")) {
                sess.setAttribute("notificationErr", "Please fill all blank.");
                response.sendRedirect("category");
            } else if (exC.getCategoryName().equals(c.getCategoryName())
                    && exC.getCategoryId() != c.getCategoryId()) {
                sess.setAttribute("notificationErr", "Category is already exist.");
                response.sendRedirect("category");
            } else if (exC.getCategoryId() == c.getCategoryId()) {
                dao.UpdateCategory(c);
                sess.setAttribute("notification", "Category updated successfully!");
                response.sendRedirect("category");
            } else {
                dao.UpdateCategory(c);
                sess.setAttribute("notification", "Category updated successfully!");
                response.sendRedirect("category");
            }
        } else if ("add".equals(service)) {
            String name = request.getParameter("categoryName").trim().replace("\\s+", " ");
            String description = request.getParameter("categoryDescription").trim().replace("\\s+", " ");
            Category c = new Category();
            c.setCategoryName(name);
            c.setDescription(description);
            if (name.isEmpty() || name == null
                    || description.isEmpty() || description == null
                    || name.equals(" ") || description.equals(" ")) {
                sess.setAttribute("notificationErr", "Please fill all blank.");
                response.sendRedirect("category");
            } else if (dao.checkCategoryExist(name.trim()).getCategoryId() != 0) {
                sess.setAttribute("notificationErr", "Category is already exist");
                response.sendRedirect("category");
            } else {
                dao.AddNewCategory(c);
                sess.setAttribute("notification", "Category add successfully!");
                response.sendRedirect("category");
            }
        }else if ("search".equals(service)) {
            String txtSearch = request.getParameter("txtSearch").trim();
            String[] listSearch = txtSearch.split(" ");
            if (listSearch.length > 0) {
                List<Category> list = dao.searchCategoryByName(listSearch);
                request.setAttribute("listCate", list);
                request.setAttribute("txtSearch", txtSearch);
                request.getRequestDispatcher("category-management.jsp").forward(request, response);
            } else {
                List<Category> list = dao.getAllCategories();
                request.setAttribute("listCate", list);
                request.setAttribute("txtSearch", txtSearch);
                request.getRequestDispatcher("category-management.jsp").forward(request, response);
            }

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
