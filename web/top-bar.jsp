<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Start Header/Navigation -->
<nav class="custom-navbar navbar navbar-expand-md navbar-dark bg-dark" aria-label="Furni navigation bar">

    <div class="container">
        <a class="navbar-brand" href="home">Furni<span>.</span></a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsFurni">
            <ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
                <li class="nav-item active">
                    <a class="nav-link" href="home">Home</a>
                </li>
                <li><a class="nav-link" href="#">Shop</a></li>
                <li><a class="nav-link" href="about.html">About us</a></li>
                <li><a class="nav-link" href="services.html">Services</a></li>
                <li><a class="nav-link" href="blog.html">Blog</a></li>
                <li><a class="nav-link" href="contact.html">Contact us</a></li>
            </ul>
            <ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
                <c:if test="${sessionScope.currentUser != null}">
                    <li><a class="nav-link" href="profile"><img src="images/user.svg"></a></li>
                    <li>
                        <a class="nav-link" href="cart">
                            <img src="images/cart.svg">
                            <c:if test="${not empty sessionScope.cart}">
                                <span class="badge bg-secondary">${fn:length(sessionScope.cart)}</span>
                            </c:if>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.currentUser == null}">
                    <li><a class="nav-link btn btn-secondary me-2" href="login">Login</a></li>
                    <li>
                        <a class="nav-link" href="cart">
                            <img src="images/cart.svg">
                            <c:if test="${not empty sessionScope.cart}">
                                <span class="badge bg-secondary">${fn:length(sessionScope.cart)}</span>
                            </c:if>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>

</nav>
<!-- End Header/Navigation -->
