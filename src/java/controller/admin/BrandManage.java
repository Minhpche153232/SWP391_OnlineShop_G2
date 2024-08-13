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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BrandDAO dao = new BrandDAO();
        String service = request.getParameter("service");
        HttpSession sess = request.getSession();
        User user = (User) sess.getAttribute("currentUser");
        if (user != null && user.getRole().equals("1")) {
            if (service == null) {
                List<Brand> list = dao.getAllBrands();
                request.setAttribute("listB", list);
                request.getRequestDispatcher("brand-list.jsp").forward(request, response);
            } else if (service.equals("details")) {
                int id = Integer.parseInt(request.getParameter("bId"));
                Brand b = dao.getBrandById(id);
                request.setAttribute("brand", b);
                request.getRequestDispatcher("update-brand.jsp").forward(request, response);
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
        }else{
            response.sendRedirect("home");
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
        if ("update".equals(service)) {
            int id = Integer.parseInt(request.getParameter("brandId"));
            String name = request.getParameter("brandName").trim().replace("\\s+", " ");
            Brand b = new Brand(id, name);
            if (name.isEmpty() || name == null || name.equals(" ")) {
                request.setAttribute("mess", "Please fill all blank.");
                request.setAttribute("brand", b);
                request.getRequestDispatcher("update-brand.jsp").forward(request, response);
            } else if (dao.checkBrandExist(name.trim()) == true) {
                request.setAttribute("mess", "Brand is already exist");
                request.setAttribute("brand", b);
                request.getRequestDispatcher("update-brand.jsp").forward(request, response);
            } else {
                dao.UpdateBrand(b);
                response.sendRedirect("brand");
            }
        } else if ("add".equals(service)) {
            String name = request.getParameter("brandName").trim().replace("\\s+", " ");
            Brand b = new Brand();
            b.setBrandName(name);
            if (name.isEmpty() || name == null || name.equals(" ")) {
                request.setAttribute("mess", "Please fill all blank.");
                request.setAttribute("brand", b);
                request.getRequestDispatcher("add-brand.jsp").forward(request, response);
            } else if (dao.checkBrandExist(name.trim()) == true) {
                request.setAttribute("mess", "Brand is already exist");
                request.setAttribute("brand", b);
                request.getRequestDispatcher("add-brand.jsp").forward(request, response);
            } else {
                dao.AddNewBrand(b);
                response.sendRedirect("brand");
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
