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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify mail</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #EFF2F1;
            }
            .register-form {
                background: #EFF2F1;
                padding: 10px;
                box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
                margin: 50px 0 50px;
            }
            .error {
                color: red;
                font-size: 0.9em;
            }
        </style>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const today = new Date().toISOString().split('T')[0];
                document.getElementById('dob').setAttribute('max', today);
            });

            document.getElementById('registerForm').addEventListener('submit', function (event) {
                if (!validateForm()) {
                    event.preventDefault();
                }
            });

        </script>
    </head>
    <body>
        <div class="container">
            <div>
               
            </div>
            <div class="row justify-content-center">
                <div class="col-md-5">
                    <div class="card mt-3 register-form">
                                            <p <c:if test="${mess eq null}">hidden</c:if>>${mess}</p>

                        <div class="card-header text-center">
                            <h2>Verify mail</h2>
                        </div>
                        <div class="card-body">

                            <c:if test="${not empty error}">
                                <div class="alert alert-danger" role="alert">
                                    ${error}
                                </div>
                            </c:if>

                            <form  action="active-account" method="POST">
                               
                              

                                <div class="form-group">
                                    <label for="email">Code</label>
                                    <input type="text" class="form-control"  name="code" placeholder="Code" required >
                                    <div id="emailError" class="error"></div>
                                </div>


                                <button type="submit" class="btn btn-primary btn-block" style="background-color: #F9BF29; border: none; color: #000; text-decoration: underline" onClick ="return validateForm()">Verify</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="js/validateSignUp.js"></script>
    </body>
</html>
