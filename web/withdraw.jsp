<%-- 
    Document   : withdraw
    Created on : Aug 24, 2024, 1:48:07 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    <title>Withdraw</title>

    <body>
        <%@include file="navbar-without-brand.jsp" %>

        <div class="container">
            <div class="row mt-5 mb-5 justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h3>Input amount:</h3>
                        </div>
                        <div class="card-body">
                            <form id="frmCreateOrder" action="wallet" method="post">
                                <div class="mb-3">
                                    <label class="form-label" for="bankCode">Bank:</label>
                                    <input class="form-control" type="text" id="bankCode" name="bankCode" value="${ba.bankName}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="cardNumber">Card number:</label>
                                    <input class="form-control" type="text" id="cardNumber" name="cardNumber" value="${ba.cardNumber}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="cardOwner">Card Owner:</label>
                                    <input class="form-control" type="text" id="cardOwner" name="cardOwner" value="${ba.cardOwner}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="amount">Amount:</label>
                                    <input class="form-control" type="number" id="amount" name="amount" min="10000" placeholder="10000">
                                </div>
                                <input type="hidden" name="action" value="withdraw">
                                <button type="submit" class="btn btn-primary">Submit</button>
                                <a href="home" class="btn btn-warning">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
