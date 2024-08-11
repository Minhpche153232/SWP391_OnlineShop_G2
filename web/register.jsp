<%-- 
    Document   : register
    Created on : Aug 10, 2024, 9:59:08 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .register-container {
                margin-top: 50px;
            }
            .register-card {
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .register-card h3 {
                text-align: center;
                margin-bottom: 30px;
            }
            .btn-register {
                width: 100%;
            }
            .login-link {
                text-align: center;
                margin-top: 15px;
            }
        </style>
    </head>
    <body>
        <div class="container register-container">
            <div class="row d-flex justify-content-center align-items-center">
                <div class="col-md-8">
                    <div class="register-card">
                        <h3>Register</h3>
                        <form action="register" method="post">
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label>Full Name: </label>
                                    <input type="text" class="form-control" name="fullname" placeholder="Full Name" required pattern="[a-zA-Z]+" title="Only letters allowed">
                                </div>
                                <div class="col-md-6">
                                    <label>User Name: </label>
                                    <input type="text" class="form-control" name="username" placeholder="User name" required pattern="^\S+$" title="No spaces allowed">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label>Password: </label>
                                    <input type="password" class="form-control" name="password" placeholder="Password" required pattern="^\S+$" title="No spaces allowed">
                                </div>
                                <div class="col-md-6">
                                    <label>Re-password: </label>
                                    <input type="password" class="form-control" name="repassword" placeholder="Re-password" required pattern="^\S+$" title="No spaces allowed">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label>Email: </label>
                                    <input type="email" class="form-control" name="email" placeholder="Email" required pattern="^\S+$" title="No spaces allowed">
                                </div>
                                <div class="col-md-6">
                                    <label>Phone number:</label>
                                    <input type="text" class="form-control" name="phone" placeholder="Phone number" required pattern="^\S+$" title="No spaces allowed">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label>Address: </label>
                                    <input type="text" class="form-control" name="address" placeholder="Address" required>
                                </div>
                                <div class="col-md-6">
                                    <label>Date of birth: </label>
                                    <input type="date" class="form-control" name="dob" placeholder="Date of birth" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    Gender: 
                                    <input type="radio" name="gender" value="true" checked> Male
                                    <input type="radio" name="gender" value="false"> FeMale
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary btn-register">REGISTER</button>
                        </form>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger mt-3">${error}</div>
                        </c:if>
                        <div class="login-link">
                            <span>Already had an account? </span><a href="login.jsp"><strong>Login now</strong></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

