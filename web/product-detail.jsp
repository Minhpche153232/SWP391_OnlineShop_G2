<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <link href="css/style.css" rel="stylesheet">
        <title>Product detail</title>

        <style>
            .button-container {
                display: flex;
                flex-wrap: wrap;
                gap: 10px; /* Adds space between buttons */
            }

            .button-container form {
                flex: 1 1 calc(20% - 10px); /* Ensures up to 5 buttons per row */
            }

            .button-container button {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                background-color: #f8f9fa;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .button-container button:hover {
                background-color: #e2e6ea;
            }
        </style>
    </head>

    <body>
        <%@include file="top-bar.jsp" %>
        <div class="product-section">
            <div class="container">
                <div class="row">
                    <!-- Product Details Section -->
                    <div class="col-md-12 col-lg-5 mb-5 mb-lg-0">
                        <h2 class="mb-6 section-title">${product.productName} (${product.productCode})</h2>
                        <p class="mb-4">${product.description}</p>
                        <h3>${product.price} VND - <span style="color: red">(${discount}%)</span></h3> 

                        <c:set var="isLoggedIn" value="${not empty sessionScope.currentUser}" />
                        <c:set var="hasSizeAndColor" value="${not empty param.size and not empty param.color}" />

                        <form action="add-cart" method="POST" onsubmit="return validateForm()">
                            <input type="hidden" name="productId" value="${product.productId}">
                            <input type="hidden" name="size" value="${param.size}">
                            <input type="hidden" name="color" value="${param.color}">
                            <button type="submit" class="btn">Add to cart</button>
                        </form>
                    </div> 

                    <!-- Image and Selection Buttons Section -->
                    <div class="col-md-12 col-lg-5 mb-5 mb-lg-0">
                        <img src="${image}" width="600" height="450" alt="alt" style="margin-bottom: 10px"/>
                        <div class="button-container">
                            <c:forEach items="${product.productDetails}" var="pd">
                                <form action="product-detail">
                                    <input type="hidden" value="${pd.productId}" name="id">
                                    <input type="hidden" name="color" value="${pd.color}">
                                    <input type="hidden" name="size" value="${pd.size}">
                                    <button
                                        <c:if test="${param.size == pd.size && param.color == pd.color}">
                                            style="background-color: #007bff; color: white; font-weight: bold;"
                                        </c:if>
                                        >
                                        ${pd.size} - ${pd.color}
                                    </button>
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal for Not Logged In -->
        <div class="modal" id="loginModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Login Required</h5>
                        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>You must be logged in to add items to the cart. Please log in to continue.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal for Missing Size or Color -->
        <div class="modal" id="sizeColorModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Selection Required</h5>
                        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Please select both a size and a color before adding the product to the cart.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/tiny-slider.js"></script>
        <script src="js/custom.js"></script>
    </body>

    <script>
                            function validateForm() {
                                var isLoggedIn = ${not empty sessionScope.currentUser ? 'true' : 'false'};
                                var hasSizeAndColor = ${not empty param.size && not empty param.color ? 'true' : 'false'};

                                if (!isLoggedIn) {
                                    $('#loginModal').modal('show');
                                    return false; // Prevent form submission
                                } else if (!hasSizeAndColor) {
                                    $('#sizeColorModal').modal('show');
                                    return false; // Prevent form submission
                                }
                                return true; // Allow form submission
                            }

    </script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</html>
