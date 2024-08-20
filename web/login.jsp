<%-- 
    Document   : login
    Created on : Aug 10, 2024, 9:58:50 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="css/tiny-slider.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/my-dropdown.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h3>Login</h3>
                        </div>
                        <div class="card-body">
                            <c:if test="${not empty sessionScope.notification}">
                                <div class="alert alert-success alert-dismissible fade show" role="alert" style="text-align: center">
                                    ${sessionScope.notification}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                                <%
                                    session.removeAttribute("notification");
                                %>
                            </c:if>
                            <c:if test="${not empty sessionScope.notificationErr}">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert" style="text-align: center">
                                    ${sessionScope.notificationErr}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                                <%
                                    session.removeAttribute("notificationErr");
                                %>
                            </c:if>
                            <form action="login" method="post">
                                <div class="mb-3">
                                    <label for="username" class="form-label">Username</label>
                                    <input type="text" class="form-control" id="username" name="username" value="${cookie.username.value}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Password</label>
                                    <input type="password" class="form-control" id="password" value="${cookie.password.value}" name="password" required>
                                </div>
                                <div class="mb-3 form-check">
                                    <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe" <c:if test="${not empty cookie.username.value}">checked</c:if>>
                                        <label class="form-check-label" for="rememberMe">Remember Me</label>
                                    </div>
                                    <button type="submit" class="btn btn-primary form-control">Login</button>
                                </form>
                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger mt-3">${errorMessage}</div>
                            </c:if>
                            <div class="mt-3" style="text-align: right">
                                Don't have an account? <a href="register">Register here</a>
                            </div>
                            <div class="mt-3" style="text-align: right">
                                Forgot your password? <a href="reset-password.jsp">Click here</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

