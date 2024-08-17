<%-- 
    Document   : top-bar
    Created on : Aug 10, 2024, 7:54:00 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Start Header/Navigation -->
<nav class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark" arial-label="Furni navigation bar">

    <div class="container">
        <a class="navbar-brand" href="home">Furni<span>.</span></a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsFurni">
            <ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
                <li style="display: flex; align-items: end; justify-items: center" id="input-search-shop"> <div class="col-auto">

                        <div class="searchbar">
                            <div class="searchbar-wrapper">
                               

                                <div class="searchbar-center">
                                    <div class="searchbar-input-spacer"></div>

                                    <input type="text" class="searchbar-input" maxlength="2048"name="q" autocapitalize="off" autocomplete="off" title="Search" role="combobox" placeholder="Search product...">
                                </div>

                                <div class="searchbar-right">
                                     <div class="searchbar-left">
                                    <div class="search-icon-wrapper">
                                        <span class="search-icon searchbar-icon">
                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                                                <path d="M15.5 14h-.79l-.28-.27A6.471 6.471 0 0 0 16 9.5 6.5 6.5 0 1 0 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z">
                                                </path>
                                            </svg>
                                        </span>
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </li>

                <li class="" id="home">
                    <a class="nav-link" href="home" >Home</a>
                </li>
                <li id="shop"><a class="nav-link" href="shop" >Shop</a></li>

            </ul>
            <ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
                <c:if test="${sessionScope.currentUser != null}">
                    <li><a class="nav-link" href="profile"><img src="images/user.svg"></a></li>
                    <li><a class="nav-link" href="cart.html"><img src="images/cart.svg"></a></li>
                    </c:if>
                    <c:if test="${sessionScope.currentUser == null}">
                    <li style="display: flex; align-items: end"><a class="nav-link btn btn-secondary" style="color: white; height: 35px;width:  75px; display: flex;
                                                                   align-items: center;    justify-content: center;" href="login">Login</a></li>
                    <li><a class="nav-link" href="cart.html"><img src="images/cart.svg"></a></li>
                    </c:if>
            </ul>
        </div>
    </div>

</nav>
<!-- End Header/Navigation -->