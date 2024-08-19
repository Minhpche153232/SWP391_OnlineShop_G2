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
                            <h1>Modern Interior <span clsas="d-block">Design Studio</span></h1>
                            <p class="mb-4">Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate velit imperdiet dolor tempor tristique.</p>
                            <p><a href="shop" class="btn btn-secondary me-2">Shop Now</a><a href="#" class="btn btn-white-outline">Explore</a></p>
                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div class="hero-img-wrap">
                            <img src="images/mlb-bg.png" class="img-fluid"  style="width: 35vw"/>
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
                                <p><a href="shop" class="btn">Explore</a></p>
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
                        <div class="row">

                            <c:forEach items="${listCheapest}" var="i">
                                <div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
                                    <a class="product-item" href="cart.html">
                                        <!--<img src="${i.product.image}" class="img-fluid product-thumbnail">-->
                                        <img src="images/air_gray_03.png" class="img-fluid product-thumbnail">
                                        <h3 class="product-title">${i.product.productName}</h3>
                                        <strong class="product-price">
                                            <fmt:formatNumber value="${i.product.price}" type="number" groupingUsed="true" /> VND
                                        </strong>

                                        <span class="icon-cross">
                                            <img src="images/cross.svg" class="img-fluid">
                                        </span>
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

        <!-- Start Why Choose Us Section -->
        <div class="why-choose-section">
            <div class="container">
                <div class="row justify-content-between">
                    <div class="col-lg-6">
                        <h2 class="section-title">Why Choose Us</h2>
                        <p>Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate velit imperdiet dolor tempor tristique.</p>

                        <div class="row my-5">
                            <div class="col-6 col-md-6">
                                <div class="feature">
                                    <div class="icon">
                                        <img src="images/truck.svg" alt="Image" class="imf-fluid">
                                    </div>
                                    <h3>Fast &amp; Free Shipping</h3>
                                    <p>Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate.</p>
                                </div>
                            </div>

                            <div class="col-6 col-md-6">
                                <div class="feature">
                                    <div class="icon">
                                        <img src="images/bag.svg" alt="Image" class="imf-fluid">
                                    </div>
                                    <h3>Easy to Shop</h3>
                                    <p>Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate.</p>
                                </div>
                            </div>

                            <div class="col-6 col-md-6">
                                <div class="feature">
                                    <div class="icon">
                                        <img src="images/support.svg" alt="Image" class="imf-fluid">
                                    </div>
                                    <h3>24/7 Support</h3>
                                    <p>Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate.</p>
                                </div>
                            </div>

                            <div class="col-6 col-md-6">
                                <div class="feature">
                                    <div class="icon">
                                        <img src="images/return.svg" alt="Image" class="imf-fluid">
                                    </div>
                                    <h3>Hassle Free Returns</h3>
                                    <p>Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate.</p>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="col-lg-5">
                        <div class="img-wrap">
                            <img src="images/blog_giay_01.jpg" alt="Image" class="img-fluid">
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- End Why Choose Us Section -->

        <!-- Start We Help Section -->
        <div class="we-help-section">
            <div class="container">
                <div class="row justify-content-between">
                    <div class="col-lg-7 mb-5 mb-lg-0">
                        <div class="imgs-grid">
                            <div class="grid grid-1"><img src="images/blog_giay_01.jpg" alt="Untree.co"></div>
                            <div class="grid grid-2"><img src="images/blog_giay_02.webp" alt="Untree.co"></div>
                            <div class="grid grid-3"><img src="images/blog_giay_03.jpg" alt="Untree.co"></div>
                        </div>
                    </div>
                    <div class="col-lg-5 ps-lg-5">
                        <h2 class="section-title mb-4">We Help You Make Modern Interior Design</h2>
                        <p>Donec facilisis quam ut purus rutrum lobortis. Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate velit imperdiet dolor tempor tristique. Pellentesque habitant morbi tristique senectus et netus et malesuada</p>

                        <ul class="list-unstyled custom-list my-4">
                            <li>Donec vitae odio quis nisl dapibus malesuada</li>
                            <li>Donec vitae odio quis nisl dapibus malesuada</li>
                            <li>Donec vitae odio quis nisl dapibus malesuada</li>
                            <li>Donec vitae odio quis nisl dapibus malesuada</li>
                        </ul>
                        <p><a herf="#" class="btn">Explore</a></p>
                    </div>
                </div>
            </div>
        </div>
        <!-- End We Help Section -->

        <!-- Start Popular Product -->
        <div class="popular-product">
            <div class="container">
                <div class="row">
                    <c:forEach items="${listCheapest}" var="i">

                        <div class="col-12 col-md-6 col-lg-4 mb-4 mb-lg-0">
                            <div class="product-item-sm d-flex">
                                <div class="thumbnail">
                                    <img src="${i.product.image}" alt="Image" class="img-fluid">
                                </div>
                                <div class="pt-3">
                                    <h3>${i.product.productName}</h3>
                                    <p>${i.product.description}</p>
                                    <p><a href="#">Read More</a></p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </div>
            </div>
        </div>
        <!-- End Popular Product -->

        <!-- Start Testimonial Slider -->
        <div class="testimonial-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-7 mx-auto text-center">
                        <h2 class="section-title">Testimonials</h2>
                    </div>
                </div>

                <div class="row justify-content-center">
                    <div class="col-lg-12">
                        <div class="testimonial-slider-wrap text-center">

                            <div id="testimonial-nav">
                                <span class="prev" data-controls="prev"><span class="fa fa-chevron-left"></span></span>
                                <span class="next" data-controls="next"><span class="fa fa-chevron-right"></span></span>
                            </div>

                            <div class="testimonial-slider">

                                <div class="item">
                                    <div class="row justify-content-center">
                                        <div class="col-lg-8 mx-auto">

                                            <div class="testimonial-block text-center">
                                                <blockquote class="mb-5">
                                                    <p>&ldquo;Donec facilisis quam ut purus rutrum lobortis. Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate velit imperdiet dolor tempor tristique. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer convallis volutpat dui quis scelerisque.&rdquo;</p>
                                                </blockquote>

                                                <div class="author-info">
                                                    <div class="author-pic">
                                                        <img src="images/person-1.png" alt="Maria Jones" class="img-fluid">
                                                    </div>
                                                    <h3 class="font-weight-bold">Maria Jones</h3>
                                                    <span class="position d-block mb-3">CEO, Co-Founder, XYZ Inc.</span>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div> 
                                <!-- END item -->

                                <div class="item">
                                    <div class="row justify-content-center">
                                        <div class="col-lg-8 mx-auto">

                                            <div class="testimonial-block text-center">
                                                <blockquote class="mb-5">
                                                    <p>&ldquo;Donec facilisis quam ut purus rutrum lobortis. Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate velit imperdiet dolor tempor tristique. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer convallis volutpat dui quis scelerisque.&rdquo;</p>
                                                </blockquote>

                                                <div class="author-info">
                                                    <div class="author-pic">
                                                        <img src="images/person-1.png" alt="Maria Jones" class="img-fluid">
                                                    </div>
                                                    <h3 class="font-weight-bold">Maria Jones</h3>
                                                    <span class="position d-block mb-3">CEO, Co-Founder, XYZ Inc.</span>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div> 
                                <!-- END item -->

                                <div class="item">
                                    <div class="row justify-content-center">
                                        <div class="col-lg-8 mx-auto">

                                            <div class="testimonial-block text-center">
                                                <blockquote class="mb-5">
                                                    <p>&ldquo;Donec facilisis quam ut purus rutrum lobortis. Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate velit imperdiet dolor tempor tristique. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer convallis volutpat dui quis scelerisque.&rdquo;</p>
                                                </blockquote>

                                                <div class="author-info">
                                                    <div class="author-pic">
                                                        <img src="images/person-1.png" alt="Maria Jones" class="img-fluid">
                                                    </div>
                                                    <h3 class="font-weight-bold">Maria Jones</h3>
                                                    <span class="position d-block mb-3">CEO, Co-Founder, XYZ Inc.</span>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div> 
                                <!-- END item -->

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Testimonial Slider -->

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

                <div class="row">

                    <div class="col-12 col-sm-6 col-md-4 mb-4 mb-md-0">
                        <div class="post-entry">
                            <a href="#" class="post-thumbnail"><img src="images/blog_giay_04.jpg" alt="Image" class="img-fluid"></a>
                            <div class="post-content-entry">
                                <h3><a href="#">First Time Home Owner Ideas</a></h3>
                                <div class="meta">
                                    <span>by <a href="#">Kristin Watson</a></span> <span>on <a href="#">Dec 19, 2021</a></span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-sm-6 col-md-4 mb-4 mb-md-0">
                        <div class="post-entry">
                            <a href="#" class="post-thumbnail"><img src="images/blog_giay_05.jpg" alt="Image" class="img-fluid"></a>
                            <div class="post-content-entry">
                                <h3><a href="#">How To Keep Your Furniture Clean</a></h3>
                                <div class="meta">
                                    <span>by <a href="#">Robert Fox</a></span> <span>on <a href="#">Dec 15, 2021</a></span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-sm-6 col-md-4 mb-4 mb-md-0">
                        <div class="post-entry">
                            <a href="#" class="post-thumbnail"><img src="images/blog_giay_06.jpg" alt="Image" class="img-fluid"></a>
                            <div class="post-content-entry">
                                <h3><a href="#">Small Space Furniture Apartment Ideas</a></h3>
                                <div class="meta">
                                    <span>by <a href="#">Kristin Watson</a></span> <span>on <a href="#">Dec 12, 2021</a></span>
                                </div>
                            </div>
                        </div>
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
