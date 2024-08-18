<!-- /*
* Bootstrap 5
* Template Name: Furni
* Template Author: Untree.co
* Template URI: https://untree.co/
* License: https://creativecommons.org/licenses/by/3.0/
*/ -->
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="author" content="Untree.co">
        <link rel="shortcut icon" href="favicon.png">

        <meta name="description" content="" />
        <meta name="keywords" content="bootstrap, bootstrap4" />

        <!-- Bootstrap CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="css/tiny-slider.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/input.css" rel="stylesheet">

        <link href="css/style.css" rel="stylesheet">
        <title>Shop product</title>
        <script>

        </script>
    </head>

    <body>

        <%@include file="top-bar.jsp" %>


        <!-- Start Hero Section -->
        <div class="hero">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-2">
                        <div class="intro-excerpt">
                            <h1>Shop</h1>
                        </div>

                    </div>
                    <div class="col-lg-7">
                        <div class="searchbar" style="width: 100%">
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
                </div>
            </div>
        </div>
        <!-- End Hero Section -->

        <form method="get" action="user-manager" class="search-form" style="display: flex; justify-content: flex-end; align-items: center; margin-top: 100px; margin-right: 50px">

             <div style="width: 150px; margin-left: 20px">
                <select name="roleSearch" class="form-select">
                    <option value="">All</option>

                    <option  
                        selected>
                        White
                    </option>
                    <option 
                        >Gray
                    </option>

                </select>
            </div>
            <div style="width: 150px; margin-left: 20px">
                <select name="roleSearch" class="form-select">
                    <option value="">All</option>

                    <option  
                        selected>
                        Nike
                    </option>
                    <option 
                        >Bitis
                    </option>

                </select>
            </div>
            <div style="width: 150px; margin-left: 20px">
                <select name="roleSearch" class="form-select">
                    <option value="">All</option>

                    <option  
                        selected>
                        Bitis 01
                    </option>
                    <option value="${r.roleId}" 
                            >Nike 01
                    </option>

                </select>
            </div>
            <div style="width: 150px; margin-left: 20px">
                <select name="roleSearch" class="form-select">
                    <option value="">All</option>

                    <option  
                        selected>
                        Male
                    </option>
                    <option value="${r.roleId}" 
                            >Female
                    </option>

                </select>
            </div>
            <button type="submit" class="btn btn-primary" style="margin-left: 20px">Search</button>
        </form>

        <div class="untree_co-section product-section before-footer-section">

            <div class="container">
                <div class="row">

                    <!-- Start Column 1 -->
                    <div class="col-12 col-md-4 col-lg-3 mb-5">
                        <a class="product-item" href="#">
                            <img src="images/blog_giay_05.jpg" class="img-fluid product-thumbnail">
                            <h3 class="product-title">Nordic Chair</h3>
                            <strong class="product-price">$50.00</strong>

                            <span class="icon-cross">
                                <img src="images/cross.svg" class="img-fluid">
                            </span>
                        </a>
                    </div> 
                    <!-- End Column 1 -->

                    <!-- Start Column 2 -->
                    <div class="col-12 col-md-4 col-lg-3 mb-5">
                        <a class="product-item" href="#">
                            <img src="images/blog_giay_05.jpg" class="img-fluid product-thumbnail">
                            <h3 class="product-title">Nordic Chair</h3>
                            <strong class="product-price">$50.00</strong>

                            <span class="icon-cross">
                                <img src="images/cross.svg" class="img-fluid">
                            </span>
                        </a>
                    </div> 
                    <!-- End Column 2 -->

                    <!-- Start Column 3 -->
                    <div class="col-12 col-md-4 col-lg-3 mb-5">
                        <a class="product-item" href="#">
                            <img src="images/blog_giay_05.jpg" class="img-fluid product-thumbnail">
                            <h3 class="product-title">Kruzo Aero Chair</h3>
                            <strong class="product-price">$78.00</strong>

                            <span class="icon-cross">
                                <img src="images/cross.svg" class="img-fluid">
                            </span>
                        </a>
                    </div>
                    <!-- End Column 3 -->

                    <!-- Start Column 4 -->
                    <div class="col-12 col-md-4 col-lg-3 mb-5">
                        <a class="product-item" href="#">
                            <img src="images/blog_giay_05.jpg" class="img-fluid product-thumbnail">
                            <h3 class="product-title">Ergonomic Chair</h3>
                            <strong class="product-price">$43.00</strong>

                            <span class="icon-cross">
                                <img src="images/cross.svg" class="img-fluid">
                            </span>
                        </a>
                    </div>
                    <!-- End Column 4 -->


                    <!-- Start Column 1 -->
                    <div class="col-12 col-md-4 col-lg-3 mb-5">
                        <a class="product-item" href="#">
                            <img src="images/blog_giay_05.jpg" class="img-fluid product-thumbnail">
                            <h3 class="product-title">Nordic Chair</h3>
                            <strong class="product-price">$50.00</strong>

                            <span class="icon-cross">
                                <img src="images/cross.svg" class="img-fluid">
                            </span>
                        </a>
                    </div> 
                    <!-- End Column 1 -->

                    <!-- Start Column 2 -->
                    <div class="col-12 col-md-4 col-lg-3 mb-5">
                        <a class="product-item" href="#">
                            <img src="images/blog_giay_05.jpg" class="img-fluid product-thumbnail">
                            <h3 class="product-title">Nordic Chair</h3>
                            <strong class="product-price">$50.00</strong>

                            <span class="icon-cross">
                                <img src="images/cross.svg" class="img-fluid">
                            </span>
                        </a>
                    </div> 
                    <!-- End Column 2 -->

                    <!-- Start Column 3 -->
                    <div class="col-12 col-md-4 col-lg-3 mb-5">
                        <a class="product-item" href="#">
                            <img src="images/blog_giay_05.jpg" class="img-fluid product-thumbnail">
                            <h3 class="product-title">Kruzo Aero Chair</h3>
                            <strong class="product-price">$78.00</strong>

                            <span class="icon-cross">
                                <img src="images/cross.svg" class="img-fluid">
                            </span>
                        </a>
                    </div>
                    <!-- End Column 3 -->

                    <!-- Start Column 4 -->
                    <div class="col-12 col-md-4 col-lg-3 mb-5">
                        <a class="product-item" href="#">
                            <img src="images/blog_giay_05.jpg" class="img-fluid product-thumbnail">
                            <h3 class="product-title">Ergonomic Chair</h3>
                            <strong class="product-price">$43.00</strong>

                            <span class="icon-cross">
                                <img src="images/cross.svg" class="img-fluid">
                            </span>
                        </a>
                    </div>
                    <!-- End Column 4 -->

                </div>
                                <div style="width: 100%; display: flex; justify-content: center; margin-top: 50px" ><a href="shop" class="btn btn-secondary me-2">View more</a></div>

            </div>
        </div>


               <%@include file="footer.jsp" %>



        <script src="js/shop.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/tiny-slider.js"></script>
        <script src="js/custom.js"></script>
    </body>

</html>
