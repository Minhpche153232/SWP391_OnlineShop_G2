/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.TypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Brand;
import models.Category;
import models.ProductDetail;
import models.Type;

/**
 *
 * @author Admin
 */
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        BrandDAO brandDAO = new BrandDAO();
        TypeDAO typeDAO = new TypeDAO();

        String price = request.getParameter("price");
        String search = request.getParameter("search");
        String sizePR = request.getParameter("size");
        String color = request.getParameter("color");
        String typeIdPR = request.getParameter("typeId");
        String categoryIdPR = request.getParameter("categoryId");
        String brandIdPR = request.getParameter("brandId");
        Integer typeId = null;
        Integer categoryId = null;
        Integer brandId = null;
        Integer size = null;
        Integer[] rangePrice = {null, null};
        if (typeIdPR != null && !typeIdPR.equals("0") && !typeIdPR.equals("")) {
            typeId = Integer.valueOf(typeIdPR);
        }
        if (categoryIdPR != null && !categoryIdPR.equals("0") && !categoryIdPR.equals("")) {
            categoryId = Integer.valueOf(categoryIdPR);
        }
        if (brandIdPR != null && !brandIdPR.equals("") && !brandIdPR.equals("0")) {
            brandId = Integer.valueOf(brandIdPR);
        }

        if (sizePR != null && !sizePR.equals("0") && !sizePR.equals("")) {
            size = Integer.valueOf(sizePR);
        }
        if (price != null && !price.equals("0") && !price.equals("")) {
            try {
                String[] range = price.split("-");
                rangePrice[0] = Integer.valueOf(range[0]);
                if (range[1] != null) {
                    rangePrice[1] = Integer.valueOf(range[1]);

                }
            } catch (Exception e) {
            }

        }
        if (rangePrice[0] == null) {
            rangePrice = null;
        }

        List<ProductDetail> listCheapest = productDAO.getTopCheapestProduct(rangePrice, search, size, color, typeId, categoryId, brandId);
        List<Category> categories = categoryDAO.getAllCategories();
        List<Brand> brands = brandDAO.getAllBrands();
        List<Type> types = typeDAO.getAllTypes();

        request.setAttribute("categories", categories);
        request.setAttribute("brands", brands);
        request.setAttribute("types", types);
        request.setAttribute("categoryId", categoryIdPR);
        request.setAttribute("brandId", brandIdPR);
        request.setAttribute("typeId", typeIdPR);
        request.setAttribute("search", search);
        request.setAttribute("price", price);
        request.setAttribute("size", size);

        if (listCheapest.size() > 12) {
            listCheapest = listCheapest.subList(0, 11);
        }
        request.setAttribute("listCheapest", listCheapest);

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
