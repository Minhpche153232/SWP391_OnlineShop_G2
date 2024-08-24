<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="author" content="Untree.co">
        <link rel="shortcut icon" href="https://cdn.printgo.vn/uploads/media/774255/logo-giay-1_1586510617.jpg">

        <meta name="description" content="" />
        <meta name="keywords" content="bootstrap, bootstrap4" />

        <!-- Bootstrap CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="css/tiny-slider.css" rel="stylesheet">
        <link href="css/input.css" rel="stylesheet">

        <link href="css/style.css" rel="stylesheet">
        <title>My shop</title>


    </head>


    <body>
        <%@include file="top-bar.jsp" %>


        <!-- Start Hero Section -->
        <div class="hero">
            <div class="container">
                <div class="row justify-content-between">
                    <div class="col-lg-5">
                        <div class="intro-excerpt">
                            <h1>Scoot Zeros Retro Portland <span clsas="d-block">Basketball Shoes</span></h1>
                            <p class="mb-4">Inspire disruption on-court with Scoot's signature kicks. A lightweight, breathable upper and ProFoam tooling equip you. </p>
                            <p><a href="shop" class="btn btn-secondary me-2">Shop Now</a><a href="#" class="btn btn-white-outline">Explore</a></p>
                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div class="hero-img-wrap">
                            <img src="images/Puma_Home2.png" class="img-fluid"  style="width: 33vw"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Hero Section -->

        <!-- Start Product Section -->
        <div class="product-section">
            <div class="container">
                <div class="row">

                    <!-- Start Column 1 -->
                    <div class="col-3">
                        <div class="row">

                            <div class="col-12">
                                <h2 class="mb-4 section-title">Nike Air Force 1 Scales will also be provided with its original white laces.</h2>
                                <p class="mb-4">Nike Air Force 1 Scales is the custom sneaker for men and women, embellished and enriched by the python print fabric in shades of brown on the toe box, midfoot, and heel. </p>

                            </div> 
                        </div> 

                    </div>
                    <!-- End Column 1 -->
                    <!--                    <form method="get" action="home" class="search-form" style="display: flex; justify-content: flex-end; align-items: center">
                    
                                            <div style="width: 150px; margin-left: 20px">
                                                <select name="brandId" class="form-select">
                                                    <option value="0" <c:if test="${ brandId eq '0'}"> selected</c:if>>All</option>
                    
                    <c:forEach items="${brands}" var="i">
                        <option value="${i.brandId}" 
                        <c:if test="${i.brandId eq brandId}"> selected</c:if>>${i.brandName}
                        </option>
                    </c:forEach>

                </select>
            </div>  <div style="width: 150px; margin-left: 20px">
                <select name="categoryId" class="form-select">
                    <option value="0" <c:if test="${ categoryId eq '0'}"> selected</c:if>>All</option>

                    <c:forEach items="${categories}" var="i">
                        <option value="${i.categoryId}" 
                        <c:if test="${i.categoryId eq categoryId}"> selected</c:if>>${i.categoryName}
                        </option>
                    </c:forEach>


                </select>
            </div>
            <div style="width: 150px; margin-left: 20px">
                <select name="typeId" class="form-select">
                    <option value="0" <c:if test="${ typeId eq '0'}"> selected</c:if>>All</option>

                    <c:forEach items="${types}" var="i">
                        <option value="${i.typeId}" 
                        <c:if test="${i.typeId eq typeId}"> selected</c:if>>${i.typeName}
                        </option>
                    </c:forEach>


                </select>
            </div>
            <button type="submit" class="btn btn-primary" style="margin-left: 20px">Search</button>
        </form>-->
                    <div class="col-8" style="margin-left: 20px">
                        <h4>Best seller</h4>

                        <div class="row">

                            <c:forEach items="${listCheapest}" var="i">
                                <div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
                                    <a class="product-item" href="product-detail?id=${i.productId}">
                                        <img style="width: 200px; height: 200px" src="${i.image}" class="img-fluid product-thumbnail">
                                        <!--<img src="images/air_gray_03.png" class="img-fluid product-thumbnail">-->
                                        <h3 class="product-title">${i.productName}</h3>
                                        <strong class="product-price">
                                            <fmt:formatNumber value="${i.price}" type="number" groupingUsed="true" /> VND
                                        </strong>
                                    </a>
                                </div> 


                            </c:forEach>

                        </div>

                    </div>

                </div>
                <div style="width: 100%; display: flex; justify-content: center; margin-top: 50px" ><a href="shop" class="btn btn-secondary me-2">View all</a></div>

            </div>
        </div>
        <!-- End Product Section -->
        <div class="product-section">
            <div class="container">
                <div class="row">
                    <div class="">
                        <h4>New products</h4>

                        <div class="row">

                            <c:forEach items="${listNewProduct}" var="i">
                                <div class="col-2">
                                    <a class="product-item" href="product-detail?id=${i.productId}">
                                        <img style="width: 200px; height: 200px" src="${i.image}" class="img-fluid product-thumbnail">
                                        <!--<img src="images/air_gray_03.png" class="img-fluid product-thumbnail">-->
                                        <h3 class="product-title">${i.productName}</h3>
                                        <strong class="product-price">
                                            <fmt:formatNumber value="${i.price}" type="number" groupingUsed="true" /> VND
                                        </strong>
                                    </a>
                                </div> 


                            </c:forEach>

                        </div>

                    </div>
                </div>

            </div>

        </div>


        <!-- Start Popular Product -->
        <div class="popular-product">
            <div class="container">
                <div class="row">
                    <c:forEach items="${listCheapest}" var="i">

                        <div class="col-12 col-md-6 col-lg-4 mb-4 mb-lg-0">
                            <div class="product-item-sm d-flex">
                                <div class="thumbnail">
                                    <img src="${i.image}" alt="Image" class="img-fluid">
                                </div>
                                <div class="pt-3">
                                    <h3>${i.productName}</h3>
                                    <p>${i.description}</p>
                                    <p><a href="#">Read More</a></p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </div>
            </div>
        </div>
        <!-- End Popular Product -->




        <!-- Start Blog Section -->
        <div class="blog-section">
            <div class="container">
                <div class="row mb-5">
                    <div class="col-md-6">
                        <h2 class="section-title">Recent Blog</h2>
                    </div>
                    <div class="col-md-6 text-start text-md-end">
                        <a href="#" class="more">View All Posts</a>
                    </div>
                </div>


            </div>
        </div>
        <!-- End Blog Section -->	

        <%@include file="footer.jsp" %>




        <script src="js/shop.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/tiny-slider.js"></script>
        <script src="js/custom.js"></script>
    </body>

</html>
