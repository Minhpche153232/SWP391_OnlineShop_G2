<%-- 
    Document   : confirm-transaction
    Created on : Aug 24, 2024, 2:13:57 PM
    Author     : admin
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
        <link href="css/input.css" rel="stylesheet">

        <link href="css/style.css" rel="stylesheet">
        <link href="css/my-dropdown.css" rel="stylesheet">
        <title>Link Bank Account</title>
    </head>
    <body>
        <%@include file="navbar-without-brand.jsp" %>
        <div class="container">
            <div class="row mt-5 mb-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h3>Your card information:</h3>
                        </div>
                        <div class="card-body">
                            <div class="mb-3">
                                <label class="form-label" for="bankCode">Bank:</label>
                                <h5 class="text-success">${ba.bankName}</h5>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="cardNumber">Card number:</label>
                                <h5 class="text-success">${ba.cardNumber}</h5>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="cardOwner">Card Owner:</label>
                                <h5 class="text-success">${ba.cardOwner}</h5>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="expertDate">Expert date:</label>
                                <h5 class="text-danger">${ba.expertDate}</h5>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="expertDate">Amount:</label>
                                <h5 class="text-danger">${requestScope.amount}</h5>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h3>Input the code we send to you:</h3>
                        </div>
                        <div class="card-body">
                            <form id="formCfmCode" action="wallet" method="post">
                                <div class="mb-3">
                                    <label class="form-label" for="codeCfmAccount">Code:</label>
                                    <input class="form-control" type="text" id="codeCfmAccount" name="codeCfmAccount" required>
                                    <c:if test="${errorMessage != null}">
                                        <h5 class="text-danger">${errorMessage}</h5>
                                    </c:if>
                                    <c:if test="${notification != null}">
                                        <h5 class="text-success">${notification}</h5>
                                    </c:if>
                                </div>
                                <input type="hidden" name="action" value="checkCodePay">
                                <button type="submit" class="btn btn-primary" name="btnSubmit" value="submit">Submit</button>
                                <button type="submit" class="btn btn-primary" name="btnSubmit" value="resend">Re-Send Code</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
