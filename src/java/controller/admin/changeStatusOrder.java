/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.OrderItem;
import models.ProductDetail;
import models.User;

/**
 *
 * @author Admin
 */
public class changeStatusOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object objUser = session.getAttribute("currentUser");
        User user = (User) objUser;
        if (user == null || user.getRole().equals("3")) {
            response.sendRedirect("/online_shop/home");
        } else {
            String orderId = request.getParameter("orderId");
            String status = request.getParameter("status");
            String statusChange = "";
            if ("pending".equals(status)) {
                statusChange = "on-going";
            } else if ("on-going".equals(status)) {
                statusChange = "success";
            }
            if ("fail".equals(status)) {
                statusChange = "fail";
            }
            OrderDAO orderDAO = new OrderDAO();
            boolean mess = orderDAO.changeStatus(statusChange, orderId);
            if(mess && statusChange.equals("success")){
                List<OrderItem> listItem =  orderDAO.getMyOrder(Integer.parseInt(orderId));
                List<ProductDetail> listDetail = orderDAO.getProductDetailMyOrder(Integer.parseInt(orderId));
                for (ProductDetail productDetail : listDetail) {
                    int unitInStock = productDetail.getUnitInStock();
                    int quantityOrder = 0;
                    for (OrderItem i : listItem) {
                        if(i.getProduct().getProductId() == productDetail.getProductId() &&  (productDetail.getColor() == null ? i.getColor() == null : productDetail.getColor().equals(i.getColor())) &&  productDetail.getSize() == i.getSize()){
                            quantityOrder = i.getQuantity();
                            break;
                        }
                    }
                    int quantity = unitInStock - quantityOrder;
                    orderDAO.changeQuantityProduct(quantity, productDetail.getProductId(), productDetail.getColor(), productDetail.getSize()+"");
                }
            }
            response.sendRedirect("admin/order-manager?message=" + mess);
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
