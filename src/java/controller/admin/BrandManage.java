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
public class BrandManage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BrandDAO dao = new BrandDAO();
        String service = request.getParameter("service");
        HttpSession sess = request.getSession();
        User user = (User) sess.getAttribute("currentUser");
        if (user != null && user.getRole().equals("1") || user != null && user.getRole().equals("2")) {
            if (service == null) {
                List<Brand> list = dao.getAllBrands();
                request.setAttribute("listB", list);
                request.getRequestDispatcher("brand-management.jsp").forward(request, response);
            } else if ("delete".equals(service)) {
                int id = Integer.parseInt(request.getParameter("bId"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                Brand b = new Brand();
                b.setBrandId(id);
                if (status == true) {
                    b.setStatus(false);
                } else {
                    b.setStatus(true);
                }
                dao.DeleteBrand(b);
                response.sendRedirect("brand");
            }
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
        BrandDAO dao = new BrandDAO();
        String service = request.getParameter("service");
        HttpSession sess = request.getSession();
        if ("update".equals(service)) {
            int id = Integer.parseInt(request.getParameter("brandId"));
            String name = request.getParameter("brandName").trim().replace("\\s+", " ");
            Brand b = new Brand(id, name);
            Brand exB = dao.checkBrandExist(name);
            if (name.isEmpty() || name == null || name.equals(" ")) {
                sess.setAttribute("notificationErr", "Please fill all blank.");
                response.sendRedirect("brand");
            } else if (exB.getBrandName().equals(b.getBrandName())
                    && exB.getBrandId() != b.getBrandId()) {
                sess.setAttribute("notificationErr", "Brand is already exist");
                response.sendRedirect("brand");
            } else if (exB.getBrandName().equals(b.getBrandName())
                    && exB.getBrandId() == b.getBrandId()) {
                dao.UpdateBrand(b);
                sess.setAttribute("notification", "Brand updated successfully!");
                response.sendRedirect("brand");
            } else {
                dao.UpdateBrand(b);
                sess.setAttribute("notification", "Brand updated successfully!");
                response.sendRedirect("brand");
            }
        } else if ("add".equals(service)) {
            String name = request.getParameter("brandName").trim().replace("\\s+", " ");
            Brand b = new Brand();
            b.setBrandName(name);
            Brand exB = dao.checkBrandExist(name);
            if (name.isEmpty() || name == null || name.equals(" ")) {
                sess.setAttribute("notificationErr", "Please fill all blank.");
                response.sendRedirect("brand");
            } else if (exB.getBrandName().equals(b.getBrandName())) {
                sess.setAttribute("notificationErr", "Brand is already exist");
                response.sendRedirect("brand");
            } else {
                dao.AddNewBrand(b);
                sess.setAttribute("notification", "Brand add successfully!");
                response.sendRedirect("brand");
            }
        } else if ("search".equals(service)) {
            String txtSearch = request.getParameter("txtSearch").trim();
            String[] listSearch = txtSearch.split(" ");
            if (listSearch.length > 0) {
                List<Brand> list = dao.searchBrandByName(listSearch);
                request.setAttribute("listB", list);
                request.setAttribute("txtSearch", txtSearch);
                request.getRequestDispatcher("brand-management.jsp").forward(request, response);
            } else {
                List<Brand> list = dao.getAllBrands();
                request.setAttribute("listB", list);
                request.setAttribute("txtSearch", txtSearch);
                request.getRequestDispatcher("brand-management.jsp").forward(request, response);
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
