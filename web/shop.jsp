<!-- /*
* Bootstrap 5
* Template Name: Furni
* Template Author: Untree.co
* Template URI: https://untree.co/
* License: https://creativecommons.org/licenses/by/3.0/
*/ -->
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

        <!-- Bootstrap CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="css/tiny-slider.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/input.css" rel="stylesheet">

        <title>Shop product</title>
        <script>

        </script>
    </head>

    <body>

        <%@include file="top-bar.jsp" %>


        <!-- Start Hero Section -->

        <!-- End Hero Section -->
        <div class="product-section">
            <div class="container">
                <div class="row">
<!--                    <div style="display: flex; justify-content: flex-end; align-items: center">




                        <c:forEach items="${brands}" var="i">

                            <a href="shop?brandId=${i.brandId}" class="a-button-filter <c:if test="${i.brandId eq brandId}"> a-button-filter-active</c:if>">${i.brandName}</a>
                        </c:forEach>





                        <c:forEach items="${categories}" var="i">
                            <a href="shop?categoryId=${i.categoryId}" class="a-button-filter <c:if test="${i.categoryId eq categoryId}"> a-button-filter-active</c:if>">${i.categoryName}</a>

                        </c:forEach>


                        <c:forEach items="${types}" var="i">

                            <a href="shop?typeId=${i.typeId}" class="a-button-filter <c:if test="${i.typeId eq typeId}"> a-button-filter-active</c:if>">${i.typeName}</a>

                        </c:forEach>


                    </div>-->
                </div>
                <div class="row " >
                    <div class="col-3 " style="height: fit-content;">
                        <div style="height: fit-content; background-color: white; padding: 5px 10px; border-radius:4px; margin-bottom: 20px ">
                            <div style="font-size: 14px; font-weight: bold; color: black">
                                Brand
                            </div>
                            <c:forEach items="${brands}" var="i">


                                <div class="form-check" style="display: flex;
                                     align-items: baseline;">
                                    <input class="form-check-input" type="checkbox"  name="brand" value="" id="flexCheckChecked" <c:if test="${i.brandId eq brandId}"> checked</c:if>>
                                        <label class="form-check-label" for="flexCheckChecked">
                                            <a class="nav-link" href="shop?price=${price}&brandId=${i.brandId}&categoryId=${categoryId}&typeId=${typeId}" style="color: black">${i.brandName}</a>
                                    </label>
                                </div>

                            </c:forEach>
                        </div>
                        <div style="height: fit-content; background-color: white; padding: 5px 10px; border-radius:4px; margin-bottom: 20px ">
                            <div style="font-size: 14px; font-weight: bold; color: black">
                                Filter prices
                            </div>


                            <div class="form-check" style="display: flex;
                                 align-items: baseline;">
                                <input class="form-check-input" type="checkbox"  name="brand" value="" id="flexCheckChecked" <c:if test="${'500000-1000000' eq price}"> checked</c:if>>
                                    <label class="form-check-label" for="flexCheckChecked">
                                        <a class="nav-link" href="shop?price=500000-1000000&brandId=${brandId}&categoryId=${categoryId}&typeId=${typeId}" style="color: black">500.000 - 1.000.000 VND</a>
                                </label>
                            </div>
                            <div class="form-check" style="display: flex;
                                 align-items: baseline;">
                                <input class="form-check-input" type="checkbox"  name="brand" value="" id="flexCheckChecked" <c:if test="${'1000000-2000000' eq price}"> checked</c:if>>
                                    <label class="form-check-label" for="flexCheckChecked">
                                        <a class="nav-link" href="shop?price=1000000-2000000&brandId=${brandId}&categoryId=${categoryId}&typeId=${typeId}" style="color: black">1.000.000 - 2.000.000 VND</a>
                                </label>
                            </div>
                            <div class="form-check" style="display: flex;
                                 align-items: baseline;">
                                <input class="form-check-input" type="checkbox"  name="brand" value="" id="flexCheckChecked" <c:if test="${'2000000-3000000' eq price}"> checked</c:if>>
                                    <label class="form-check-label" for="flexCheckChecked">
                                        <a class="nav-link" href="shop?price=2000000-3000000&brandId=${brandId}&categoryId=${categoryId}&typeId=${typeId}" style="color: black">2.000.000 - 3.000.000 VND</a>
                                </label>
                            </div>
                            <div class="form-check" style="display: flex;
                                 align-items: baseline;">
                                <input class="form-check-input" type="checkbox"  name="brand" value="" id="flexCheckChecked" <c:if test="${'4000000' eq price}"> checked</c:if>>
                                    <label class="form-check-label" for="flexCheckChecked">
                                        <a class="nav-link" href="shop?&price=4000000&brandId=${brandId}&categoryId=${categoryId}&typeId=${typeId}" style="color: black">Bigger 4.000.000 VND</a>
                                </label>
                            </div>

                        </div>
                        
                        <div style="height: fit-content; background-color: white; padding: 5px 10px; border-radius:4px; margin-bottom: 20px ">
                            <div style="font-size: 14px; font-weight: bold; color: black">
                                Category
                            </div>
                            <c:forEach items="${categories}" var="i">
                                <div class="form-check" style="display: flex;
                                     align-items: baseline;">
                                    <input class="form-check-input" type="checkbox"  name="brand" value="" id="flexCheckChecked" <c:if test="${i.categoryId eq categoryId}"> checked</c:if>>
                                        <label class="form-check-label" for="flexCheckChecked">
                                            <a class="nav-link" href="shop?price=${price}&brandId=${brandId}&categoryId=${i.categoryId}&typeId=${typeId}" style="color: black">${i.categoryName}</a>
                                    </label>
                                </div>

                            </c:forEach>
                        </div>

                        <div style="height: fit-content; background-color: white; padding: 5px 10px; border-radius:4px ; margin-bottom: 20px">
                            <div style="font-size: 14px; font-weight: bold; color: black">
                                Type
                            </div>
                            <c:forEach items="${types}" var="i">
                                <div class="form-check" style="display: flex;
                                     align-items: baseline;">
                                    <input class="form-check-input" type="checkbox"  name="brand" value="" id="flexCheckChecked" <c:if test="${i.typeId eq typeId}"> checked</c:if>>
                                        <label class="form-check-label" for="flexCheckChecked">
                                            <a class="nav-link" href="shop?price=${price}&brandId=${brandId}&categoryId=${categoryId}&typeId=${i.typeId}" style="color: black">${i.typeName}</a>
                                    </label>
                                </div>

                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-8 ">

                        <div class="row">
                            <c:forEach items="${listCheapest}" var="i">
                                <div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
                                    <a class="product-item" href="product-detail?id=${i.productId}">
                                        <img src="${i.image}" class="img-fluid product-thumbnail">
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
        <%@include file="footer.jsp" %>



        <script src="js/shop.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/tiny-slider.js"></script>
        <script src="js/custom.js"></script>
    </body>

</html>
