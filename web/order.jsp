<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map" %>
<%@page import="models.CartItem" %>
<%@page import="models.ProductDetailKey" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



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
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
        <title>Furni Cart</title>

        <script>
            function checkEnter(event) {
                if (event.keyCode === 13) {
                    event.preventDefault(); // Ngăn không cho form gửi đi tự động khi nhấn Enter
                    document.getElementById("searchForm").submit(); // Gửi form đến servlet order
                }
            }
        </script>
    </head>

    <body>

        <jsp:include page="top-bar.jsp"></jsp:include>

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
                <div style="width: 100%; padding-top: 5px; display: flex; justify-content: space-around; background-color: white">
                    <a class="nav-link" href="order?status=pending" style="padding-bottom: 0"> <div  style="font-size: 16px; color: black;
                                                                                                     <c:if test="${status eq 'pending'}">color:#a00000;font-weight: bold;  border-bottom: 2px solid  #a00000</c:if>; padding-bottom: 10px;">Pending</div></a>
                    <a class="nav-link" href="order?status=on-going" style="padding-bottom: 0"><div style="font-size: 16px; color: black;<c:if test="${status eq 'on-going'}">font-weight: bold;color:#a00000; border-bottom: 2px solid #a00000</c:if>; padding-bottom: 10px; ">On-going</div>
                        </a>
                        <a class="nav-link" href="order?status=success" style="padding-bottom: 0"><div style="font-size: 16px;  color: black; <c:if test="${status eq 'success'}">font-weight: bold;color:#a00000;border-bottom: 2px solid #a00000</c:if>; padding-bottom: 10px;">Success</div>
                        </a>
                        <a class="nav-link" href="order?status=success" style="padding-bottom: 0"><div style="font-size: 16px; color: black; <c:if test="${status eq 'fail'}">font-weight: bold;color:#a00000;border-bottom: 2px solid #a00000</c:if>; padding-bottom: 10px;">Fail</div>
                        </a>
                    </div>
                    <div class="search-order-item">
                        <form action="order" method="get" id="searchForm">
                            <div class="div-input-custom-no-outline">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                <input class="input-custom-no-outline" value="${search}" name="search" id="search" type="text" placeholder="Search Order ID" onkeypress="checkEnter(event)"/>
                            </div>  
                        </form>
                    </div>
                    <div class="row mb-5">

                        <div class="site-blocks-table">

                        <c:choose>
                            <c:when test="${empty listOrderDetail}">
                                <div colspan="6" class="text-center">Your cart is empty.</div>

                            </c:when>
                            <c:otherwise>
                                <c:forEach var="i" items="${listOrderDetail}">
                                    <div>
                                        <div class="header-order-item row" style="--bs-gutter-x: 0">
                                            <div class="col-10" >Order code: ${i.order.orderId}</div>
                                            <div class="col-2">
                                                <c:if test="${i.order.status eq 'pending'}">
                                                    <span style="color: black; font-size:  16px"><i class="fa-solid fa-truck-fast"></i> Order is pending</span>
                                                </c:if>
                                                <c:if test="${i.order.status eq 'on-going'}">
                                                    <span style="color: blue; font-weight: bold"><i class="fa-solid fa-truck-fast"></i>Order is on going</span>
                                                </c:if>
                                                <c:if test="${i.order.status eq 'success'}">
                                                    <span style="color: green; font-weight: bold"><i class="fa-solid fa-truck-fast"></i>Order is success</span>
                                                </c:if>
                                                <c:if test="${i.order.status eq 'fail'}">
                                                    <span style="color: #A00000; font-size:  16px"><i class="fa-solid fa-truck-fast"></i>Order is fail</span>
                                                </c:if>
                                            </div>

                                        </div>
                                        <div class="order-item row" style="--bs-gutter-x: 0">
                                            <div class="col-2">
                                                <img style="width: 200px; height: 200px" src="https://product.hstatic.net/200000642007/product/43bld_3asxcdn3n_1_d82520f09e3a4d35bad2314f520ec660_b16b22cbd3a448db96bfb816700253d3_master.jpg" alt="Image" class="img-fluid">
                                            </div>
                                            <div class="product-name col-8" style="padding-top: 20px">
                                                <h2 class="h5 text-black">${i.product.productName}</h2>
                                                <p>Size: ${i.size}, Color: ${i.color}</p>
                                                <p>x ${i.quantity}</p>

                                            </div>
                                            <div class="col-2" style="display: flex;align-items: center; font-weight: bold; color: black"><fmt:formatNumber value="${i.product.price}" type="number" groupingUsed="true" /> VND</div>
                                        </div>
                                        <div class="footer-order-item ">
                                            <div style="font-size: 18px; margin-bottom: 10px">
                                                <i class="fa-solid fa-circle-dollar-to-slot" style="color: #A00000"></i> Total Price: <span style="color: #A00000"><fmt:formatNumber value="${(i.totalPrice)}" type="number" groupingUsed="true" /> VND</span>
                                            </div>
                                                <div  <c:if test="${sessionScope.currentUser.role eq '1'}">hidden</c:if>>
                                                <button class="btn-custom" >Re-order</button>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
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
