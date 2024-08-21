<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map" %>
<%@page import="models.CartItem" %>
<%@page import="models.ProductDetailKey" %>

<%
    // Retrieve the cart from the session
    Map<ProductDetailKey, CartItem> cart = (Map<ProductDetailKey, CartItem>) request.getAttribute("cart");

double subtotal = 0.0;
if (cart != null && !cart.isEmpty()) {
    for (CartItem item : cart.values()) {
        double price = item.getProductDetail().getProduct().getPrice();
        double discount = item.getProductDetail().getDiscount();
        double discountedPrice = price - (price * discount / 100);
        subtotal += item.getQuantity() * discountedPrice;
    }
}

%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="author" content="Untree.co">
        <link rel="shortcut icon" href="favicon.png">
        <meta name="description" content="" />
        <meta name="keywords" content="bootstrap, bootstrap4" />
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="css/tiny-slider.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/input.css" rel="stylesheet">

        <title>Furni Cart</title>
    </head>

    <body>

        <jsp:include page="top-bar.jsp"></jsp:include>

            <!-- Start Hero Section -->
            <div class="hero">
                <div class="container">
                    <div class="row justify-content-between">
                        <div class="col-lg-5">
                            <div class="intro-excerpt">
                                <h1>Cart</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Hero Section -->

            <div class="untree_co-section before-footer-section">
                <div class="container">
                <c:if test="${not empty sessionScope.notification}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert" style="text-align: center">
                        ${sessionScope.notification}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <%
                        session.removeAttribute("notification");
                    %>
                </c:if>
                <c:if test="${not empty sessionScope.notificationErr}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert" style="text-align: center">
                        ${sessionScope.notificationErr}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <%
                        session.removeAttribute("notificationErr");
                    %>
                </c:if>

                <div class="row mb-5">

                    <div class="site-blocks-table">
                        <table class="table">
                            <thead>
                                <tr>                                                                        <th class="product-thumbnail">Order</th>

                                    <th class="product-thumbnail">Customer</th>


                                    <th class="product-quantity">User name</th>
                                    <th class="product-total">Total</th>
                                    <th class="product-remove">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${empty listOrderDetail}">
                                        <tr>
                                            <td colspan="6" class="text-center">Your cart is empty.</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="i" items="${listOrderDetail}">
                                            <tr >
                                                <td><a href="order?userId=${i.user.userId}&status=${i.status}">Order: ${(i.orderId)}</a></td>
                                                <td>${i.user.fullname} </td>

                                                <td>${i.user.userName} </td>

                                                <td>${(i.totalPrice)} VND</td>
                                                <td><form action="litsOrder" method="post">
                                                        <input hidden type="text" name="status"value="${i.status}"/>
                                                        <input hidden type="text" name="order"value="${(i.orderId)}"/>

                                                        <button class="btn" style="cursor: pointer">${i.status} </button></form></td>

                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row mb-5">
                            <div class="col-md-6">
                                <a href="" class="btn btn-outline-black btn-sm btn-block">Continue Shopping</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 pl-5">
                        <div class="row justify-content-end">
                            <div class="col-md-7">
                                <div class="row">
                                    <div class="col-md-12 text-right border-bottom mb-5">
                                        <h3 class="text-black h4 text-uppercase">Cart Totals</h3>
                                    </div>
                                </div>

                                <div class="row mb-5">
                                    <div class="col-md-6">
                                        <span class="text-black">Total</span>
                                    </div>
                                    <div class="col-md-6 text-right">
                                        <strong class="text-black"><%= subtotal %> VND</strong>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <button class="btn btn-black btn-lg py-3 btn-block" onclick="window.location = 'checkout.html'">Proceed To Checkout</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include> 
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/tiny-slider.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>
