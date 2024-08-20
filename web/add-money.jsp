<%-- 
    Document   : add-money
    Created on : Aug 19, 2024, 7:26:07 AM
    Author     : catmi
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
    <title>Deposit</title>

    <body>
        <%@include file="top-bar.jsp" %>

        <div class="container">
            <div class="row mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h3>Input amount:</h3>
                        </div>
                        <div class="card-body">
                            <form id="frmCreateOrder" action="#" method="post">
                                <div class="mb-3">
                                    <label class="form-label" for="amount">Amount:</label>
                                    <input class="form-control" type="number" id="amount" name="amount" min="10000" value="10000">
                                </div>
                                <button type="button" class="btn btn-primary">Submit</button>
                                <a href="home" class="btn btn-warning">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h3>Scan QR Code:</h3>
                        </div>
                        <div class="card-body">
                            <img src="/web/images/orCode.jpg" alt="alt"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
        <script type="text/javascript">
            $("#frmCreateOrder").submit(function () {
                var postData = $("#frmCreateOrder").serialize();
                var submitUrl = $("#frmCreateOrder").attr("action");
                $.ajax({
                    type: "POST",
                    url: submitUrl,
                    data: postData,
                    dataType: 'JSON',
                    success: function (x) {
                        if (x.code === '00') {
                            if (window.vnpay) {
                                vnpay.open({width: 768, height: 600, url: x.data});
                            } else {
                                location.href = x.data;
                            }
                            return false;
                        } else {
                            alert(x.Message);
                        }
                    }
                });
                return false;
            });
        </script> 
    </body>
</html>
