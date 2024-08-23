package controller.admin;

import dao.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import models.Product;
import models.ProductDetail;
import models.User;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/admin/product-detail"})
public class ProductDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object objUser = session.getAttribute("currentUser");
        User user = (User) objUser;
        if (user == null || user.getRole().equals("3")) {
            response.sendRedirect("/online_shop/home");
        } else {
            String productIdStr = request.getParameter("productId");
            if (productIdStr != null) {
                int productId = Integer.parseInt(productIdStr);
                ProductDAO productDAO = new ProductDAO();
                Product product = productDAO.getById(productId);
                if (product != null) {
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("product-detail.jsp").forward(request, response);
                } else {
                    response.sendRedirect("404.jsp");
                }
            } else {
                response.sendRedirect("404.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDAO productDAO = new ProductDAO();
        String action = request.getParameter("action");
        if (action.equals("add")) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int size = Integer.parseInt(request.getParameter("size"));
            String color = request.getParameter("color");
            int unitInStock = Integer.parseInt(request.getParameter("unitInStock"));
            Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            String uploadPath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + fileName;

            File file = new File(uploadPath);
            filePart.write(uploadPath);

            String imagePath = "images/" + fileName;
            ProductDetail existingDetail = productDAO.getProductDetailBySizeAndColor(productId, size, color);

            if (existingDetail != null) {
                // Update the unit in stock
                existingDetail.setUnitInStock(existingDetail.getUnitInStock() + unitInStock);

                // Update the image only if a new image link is provided
                if (imagePath != null && !imagePath.trim().isEmpty()) {
                     existingDetail.setImage(imagePath);
                } else {
                    existingDetail.setImage(existingDetail.getImage());
                }

                // Update the product detail in the database
                try {
                    productDAO.updateProductDetail(existingDetail);
                    session.setAttribute("notification", "Updated successfully!");
                } catch (Exception e) {
                    session.setAttribute("notificationErr", "Updated failed!");
                }

            } else {
                // Create a new product detail
                ProductDetail newDetail = new ProductDetail(productId, size, color, unitInStock, imagePath);
                productDAO.addProductDetail(newDetail);
                session.setAttribute("notification", "Create successfully!");
            }

            response.sendRedirect(request.getContextPath() + "/admin/product-detail?productId=" + productId);
        } else if (action.equals("delete")) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int size = Integer.parseInt(request.getParameter("size"));
            String color = request.getParameter("color");

            try {
                productDAO.deleteProductDetail(productId, size, color);
                session.setAttribute("notification", "Product detail deleted successfully!");
            } catch (Exception e) {
                session.setAttribute("notificationErr", "Failed to delete product detail.");
            }

            response.sendRedirect(request.getContextPath() + "/admin/product-detail?productId=" + productId);

        }
    }

}
