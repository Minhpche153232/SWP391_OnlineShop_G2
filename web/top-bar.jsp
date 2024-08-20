<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .dropdown:hover .dropdown-menu {
        display: block;
    }

    .dropdown-menu {
        margin-top: 0; /* Adjust if needed */
    }
</style>
<!-- Start Header/Navigation -->
<div>
    <nav class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark" arial-label="Furni navigation bar" style="background-color: #202020 !important">

        <div class="container">
            <a class="navbar-brand" href="home">Furni<span>.</span></a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsFurni">
                <ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">                               
                    <form action="shop">

                        <li style="display: flex; align-items: end; justify-items: center"> <div class="col-auto">

                                <div class="searchbar">
                                    <div class="searchbar-wrapper">


                                        <div class="searchbar-center">
                                            <div class="searchbar-input-spacer"></div>

                                            <input value="${search}" type="text" class="searchbar-input" maxlength="2048"name="search" autocapitalize="off" autocomplete="off" title="Search" role="combobox" placeholder="Search product...">
                                        </div>
                                        <button type="submit" style="border: none;
                                                background-color: inherit;">
                                            <div class="searchbar-right">
                                                <div class="searchbar-left">
                                                    <div class="search-icon-wrapper">

                                                        <span class="search-icon searchbar-icon" >
                                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                                                                <path d="M15.5 14h-.79l-.28-.27A6.471 6.471 0 0 0 16 9.5 6.5 6.5 0 1 0 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z">
                                                                </path>
                                                            </svg>
                                                        </span>

                                                    </div>
                                                </div>
                                            </div>
                                        </button>
                                    </div>
                                </div>
                            </div>

                        </li>
                    </form>

                    <li class="" id="home">
                        <a class="nav-link" href="home" >Home</a>
                    </li>
                    <li id="shop"><a class="nav-link" href="shop" >Shop</a></li>

                </ul>
                <ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
                    <c:if test="${sessionScope.currentUser != null}">
                        <li>
                            <div class="dropdown">
                                <a class="nav-link dropdown-toggle" href="profile" type="button" id="dropdownMenuButton" aria-haspopup="true" aria-expanded="false" style="margin-bottom: 0"><img src="images/user.svg"></a>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <c:if test="${sessionScope.currentUser !=  null}">
                                        <c:if test="${sessionScope.currentUser.role == 3}">
                                            <a  class="dropdown-item"  href="order">My order</a>
                                        </c:if>
                                        <c:if test="${sessionScope.currentUser.role == 1}">
                                            <a  class="dropdown-item"  href="admin/dashboard">Dashboard</a>
                                        </c:if>
                                    </c:if>
                                    <a  class="dropdown-item"  href="profile">Profile</a>
                                    <a class="dropdown-item" href="logout">Logout</a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="dropdown">
                                <a class="nav-link dropdown-toggle" href="#" type="button"
                                   id="dropdownMenuButton" aria-haspopup="true" aria-expanded="false" style="margin-bottom: 0">${sessionScope.currentUser.balance} VND</a>
                            </div>
                        </li>
                        <li><a class="nav-link" href="cart"><img src="images/cart.svg"></a></li>
                        </c:if>
                        <c:if test="${sessionScope.currentUser == null}">
                        <li style="display: flex; align-items: end">
                            <a class="nav-link btn btn-secondary" style="color: white; height: 35px;width:  75px; display: flex;
                               align-items: center;    justify-content: center;" href="login">Login</a></li>
                        <li><a class="nav-link" href="cart"><img src="images/cart.svg"></a></li>
                        </c:if>
                </ul>
            </div>
        </div>

    </nav>
    <div style="width: 100%; height: 50px; background-color: white !important;    display: flex;
         align-items: center;
         justify-content: flex-end;
         padding-right: 30vh;
         ">

        <div class="dropdown">
            <p class="p-click dropdown-toggle" type="button" id="dropdownMenuButton" aria-haspopup="true" aria-expanded="false" style="margin-bottom: 0">
                <c:if test="${brand.brandName != null}">
                    ${brand.brandName}
                </c:if>
                <c:if test="${brand.brandName == null}">
                    All Brand
                </c:if>
            </p>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <c:forEach items="${brands}" var="i">
                    <a class="dropdown-item" href="shop?brandId=${i.brandId}">${i.brandName}</a>
                </c:forEach>
            </div>

        </div>
        <div class="dropdown">
            <p class="p-click dropdown-toggle" type="button" id="dropdownMenuButton" aria-haspopup="true" aria-expanded="false" style="margin-bottom: 0">
                All categories
            </p>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <c:forEach items="${categories}" var="i">
                    <a class="dropdown-item" href="shop?categoryId=${i.categoryId}">${i.categoryName}</a>
                </c:forEach>
            </div>

        </div>
        <div class="dropdown">
            <p class="p-click dropdown-toggle" type="button" id="dropdownMenuButton" aria-haspopup="true" aria-expanded="false" style="margin-bottom: 0">
                ${types[0].typeName}
            </p>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <c:forEach items="${types}" var="i">
                    <a class="dropdown-item" href="shop?typeId=${i.typeId}">${i.typeName}</a>
                </c:forEach>
            </div>

<<<<<<< HEAD
=======
            </ul>
            <ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
                <c:if test="${sessionScope.currentUser != null}">
                    <li><a class="nav-link" href="profile"><img src="images/user.svg"></a></li>
                    <li><a class="nav-link" href="cart.html"><img src="images/cart.svg"></a></li>
                        </c:if>
                        <c:if test="${sessionScope.currentUser == null}">
                    <li class="dropdown">
                        <a class="btn btn-secondary dropdown-toggle dropdown-button">Join us</a>
                        <div class="dropdown-content dropdown-content">
                            <a class="" href="login">Login</a>
                            <a class="" href="register">Register</a>
                        </div>
                    </li>
                    <li><a class="nav-link" href="cart.html"><img src="images/cart.svg"></a></li>
                    </c:if>
            </ul>
>>>>>>> parent of 10f9cf5 (Config wallet)
        </div>
    </div>

    <!-- End Header/Navigation -->
