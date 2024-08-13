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
        if (service == null) {
            List<Category> list = dao.getAllCategories();
            request.setAttribute("listCate", list);
            request.getRequestDispatcher("category-list.jsp").forward(request, response);
        } else if (service.equals("details")) {
            int id = Integer.parseInt(request.getParameter("cateId"));
            Category c = dao.getCategoryById(id);
            request.setAttribute("cate", c);
            request.getRequestDispatcher("update-category.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO dao = new CategoryDAO();
        String service = request.getParameter("service");
        if ("update".equals(service)) {
            int id = Integer.parseInt(request.getParameter("cateId"));
            String name = request.getParameter("cateName");
            String description = request.getParameter("description");
            Category c = new Category(id, name, description);
            if(name.isEmpty() || name == null || description.isEmpty() || description == null){
                request.setAttribute("mess", "Please fill all blank.");
                request.setAttribute("cate", c);
                request.getRequestDispatcher("update-category.jsp").forward(request, response);
            }
            if (dao.checkCategoryExist(name.trim()) == true) {
                request.setAttribute("mess", "Category is already exist");
                request.setAttribute("cate", c);
                request.getRequestDispatcher("update-category.jsp").forward(request, response);
            }else{
                dao.UpdateCategory(c);
                response.sendRedirect("category");
            }
        } else {

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
