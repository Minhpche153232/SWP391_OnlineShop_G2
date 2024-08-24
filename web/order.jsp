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
            function changeStatus(orderId) {

                window.location.href = "cancelOrder?orderId=" + orderId;
            }
        </script>
    </head>

    <body>

        <jsp:include page="top-bar.jsp"></jsp:include>

            <div class="untree_co-section before-footer-section">
                <div class="container">

                    <!-- Display Notifications -->
                <c:if test="${message eq true}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert" style="text-align: center">
                        Cancel order successfully
                        <button type="button" class="btn-danger" data-dismiss="alert" aria-label="Close">x</button>
                    </div>
                    <%
                        session.removeAttribute("notification");
                    %>
                </c:if>
                <c:if test="${message eq false}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert" style="text-align: center">
                        Cancel order fail

                        <button type="button" class="btn-danger" data-dismiss="alert" aria-label="Close">x</button>
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
                        <a class="nav-link" href="order?status=fail" style="padding-bottom: 0"><div style="font-size: 16px; color: black; <c:if test="${status eq 'fail'}">font-weight: bold;color:#a00000;border-bottom: 2px solid #a00000</c:if>; padding-bottom: 10px;">Fail</div>
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
                                            <div class="col-10 row" ><div class="col-2">Order code: ${i.order.orderId}</div> 
                                                <div  <c:if test="${sessionScope.currentUser.role ne '3'}">hidden</c:if> class="col-5">
                                                    <button  type="button"  class="btn-custom"  <c:if test="${ i.order.status eq 'success' or i.order.status eq 'fail'}">hidden</c:if>   data-bs-toggle="modal" data-bs-target="#staticBackdrop" >Cancel order</button>
                                                    </div>

                                                </div>
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
                                                <img style="width: 200px; height: 200px" src="${i.product.image}" alt="Image" class="img-fluid">
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

                                                    <form action="add-cart" method="POST" onsubmit="return validateForm()">
                                                        <input type="hidden" name="productId" value="${i.product.productId}">
                                                    <input type="hidden" name="size" value="${i.size}">
                                                    <input type="hidden" name="color" value="${i.color}">
                                                    <button class="btn-custom" >Re-order</button>
                                                </form>
                                            </div>

                                        </div>
                                    </div>
                                    <!-- Modal -->
                                    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="staticBackdropLabel">Cancel order</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    Do you want to cancel this order
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    <button type="button" class="btn btn-primary" style="background-color: #AA0000 !important; color: white" onclick="changeStatus('${i.order.orderId}')">Cancel now</button>
                                                </div>
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
