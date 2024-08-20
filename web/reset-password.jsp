<%-- 
    Document   : reset-password
    Created on : Aug 15, 2024, 5:09:20 PM
    Author     : catmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="author" content="Untree.co">
        <link rel="shortcut icon" href="https://cdn.printgo.vn/uploads/media/774255/logo-giay-1_1586510617.jpg">

        <meta name="description" content="" />
        <meta name="keywords" content="bootstrap, bootstrap4" />

        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="css/tiny-slider.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/my-dropdown.css" rel="stylesheet">
        <title>Forget Password</title>
    </head>
    <body>
        <%@include file="top-bar.jsp" %>
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <c:if test="${getEmail == null}">
                            <div class="card-header">
                                <h3>Enter your Email or Username</h3>
                            </div>
                            <div class="card-body">
                                <form action="resetpw" method="post">
                                    <div class="mb-3">
                                        <c:if test="${not empty errorMessage}">
                                            <div class="alert alert-danger mt-3">${errorMessage}</div>
                                        </c:if>
                                        <label class="form-label" for="txtInput">Email or Username:</label>
                                        <input type="text" class="form-control" id="txtInput" name="txtInput" value="${txtInput}" required>
                                    </div>
                                    <input type="hidden" name="action" value="checkUser">
                                    <button type="submit" class="btn btn-primary form-control">Send</button>
                                </form>
                                <div class="mt-3" style="text-align: center">
                                    <a class="text-decoration-none" href="login">Back to login</a>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${getEmail == 'true'}">
                            <div class="card-header">
                                <h3>Enter the code send to ${sessionScope.emailView}</h3>
                            </div>
                            <div class="card-body">
                                <form action="resetpw" method="post">
                                    <div class="mb-3">
                                        <c:if test="${not empty errorMessage}">
                                            <div class="alert alert-danger mt-3">${errorMessage}</div>
                                        </c:if>
                                        <label class="form-label" for="codeInput">Code:</label>
                                        <input type="text" class="form-control" id="codeInput" name="codeInput">
                                    </div>
                                    <input type="hidden" name="action" value="checkCode">
                                    <div class="d-flex justify-content-around">
                                        <button type="submit" class="btn btn-primary form-control" name="btnSubmit" value="send">Send</button>
                                        <button type="submit" class="btn btn-primary form-control" name="btnSubmit" value="resend">Re-send code</button>
                                    </div>
                                </form>
                                <div class="mt-3" style="text-align: center">
                                    <a class="text-decoration-none" href="login">Back to login</a>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/tiny-slider.js"></script>
    <script src="js/custom.js"></script>
</html>
