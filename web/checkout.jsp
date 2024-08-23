<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="author" content="Untree.co">
        <title>Checkout</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

        <jsp:include page="top-bar.jsp"></jsp:include>

            <div class="hero">
                <div class="container">
                    <div class="row justify-content-between">
                        <div class="col-lg-5">
                            <div class="intro-excerpt">
                                <h1>Checkout</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
        <div class="untree_co-section">
            <div class="container">
                <form action="checkout" method="post">
                    <div class="row mb-5">
                        <div class="col-md-6 mb-5 mb-md-0">
                            <h2 class="h3 mb-3 text-black">Shipping Information</h2>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Full Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="fullName" value="${sessionScope.currentUser.fullname}" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Email</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="email" class="form-control" name="email" value="${sessionScope.currentUser.email}" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Phone</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="phone" value="${sessionScope.currentUser.phone}" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Address</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" name="address" value="${sessionScope.currentUser.address}" required>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">
                            <div class="row mb-5">
                                <div class="col-md-12">
                                    <h2 class="h3 mb-3 text-black">Your Order</h2>
                                    <div class="p-3 p-lg-5 border">
                                        <table class="table site-block-order-table mb-5">
                                            <thead>
                                            <th>Product</th>
                                            <th></th>
                                            <th>Total</th>
                                            <th>Size</th>
                                            <th>Color</th>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="entry" items="${cart.entrySet()}">
                                                    <tr>
                                                        <td>${entry.value.productDetail.product.productName} <strong class="mx-2">x</strong> ${entry.value.quantity}</td>
                                                        <td><img src="${entry.value.productDetail.image}" alt="alt" height="50"/></td>
                                                        <td>${(entry.value.quantity * entry.value.productDetail.product.price) - (entry.value.quantity * entry.value.productDetail.product.price) * entry.value.productDetail.discount / 100} VND</td>
                                                        <td>${entry.value.productDetail.size}</td>
                                                        <td>${entry.value.productDetail.color}</td>
                                                    </tr>
                                                </c:forEach>
                                                <tr>
                                                    <td class="text-black font-weight-bold"><strong>Total</strong></td>
                                                    <td class="text-black font-weight-bold"><strong>${subtotal} VND</strong></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <div class="form-group">
                                            <<input type="hidden" name="subtotal" value="${subtotal}">
                                            <button type="submit" class="btn btn-black btn-lg py-3 btn-block">Place Order</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>
