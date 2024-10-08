<%-- 
    Document   : new-password
    Created on : Aug 19, 2024, 5:41:51 AM
    Author     : catmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        <title>Forget Password</title>
    </head>
    <body>
        <%@include file="navbar-without-brand.jsp" %>
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <div class="card">
                            <div class="card-header">
                                <h3>Enter new password:</h3>
                            </div>
                            <div class="card-body">
                                <form action="resetpw" method="post">
                                    <div class="mb-3">
                                        <c:if test="${not empty errorMessage}">
                                            <div class="alert alert-danger mt-3">${errorMessage}</div>
                                        </c:if>
                                            <label class="form-label" for="newpass">New password:</label>
                                            <input type="password" class="form-control" id="newpass" name="newpass" value="${newpass}" required>
                                            <label class="form-label" for="confirmPass">Confirm new password:</label>
                                            <input type="password" class="form-control" id="confirmPass" name="confirmPass" value="${confirmPass}" required>
                                    </div>
                                    <input type="hidden" name="action" value="updatePass">
                                    <button type="submit" class="btn btn-primary form-control">Submit</button>
                                </form>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/tiny-slider.js"></script>
    <script src="js/custom.js"></script>
</html>