<%-- 
    Document   : link-bankaccount
    Created on : Aug 23, 2024, 8:30:43 PM
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
    <title>Link Bank Account</title>

    <body>
        <%@include file="navbar-without-brand.jsp" %>

        <div class="container">
            <div class="row mt-5 mb-5">
                <div class="col-md-6">
                    <div class="card">
                        <c:if test="${not empty sessionScope.notification}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert" style="text-align: center">
                                ${sessionScope.notification}
                            </div>
                            <%
                                session.removeAttribute("notification");
                            %>
                        </c:if>
                        <c:if test="${not empty sessionScope.notificationErr}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="text-align: center">
                                ${sessionScope.notificationErr}
                                <button type="button" class="btn-danger" data-dismiss="alert" aria-label="Close">x</button>
                            </div>
                            <%
                                session.removeAttribute("notificationErr");
                            %>
                        </c:if>
                        <div class="card-header">
                            <h3>Input your card information:</h3>
                        </div>
                        <div class="card-body">
                            <form id="formCreate" action="wallet" method="post">
                                <div class="mb-3">
                                    <label class="form-label" for="bankCode">Bank:</label>
                                    <select class="form-select" name="bankCode" id="bankSelect">
                                        <c:if test="${baTmp == null}">
                                            <option value="0" selected hidden>Select bank</option>
                                        </c:if>
                                        <c:forEach items="${listBank}" var="b">
                                            <option value="${b.bankCode}">${b.bankName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="cardNumber">Card number:</label>
                                    <input class="form-control" type="text" id="cardNumber" name="cardNumber" 
                                           value="${baTmp.cardNumber}" required>
                                    <h5 class="text-danger">${cardNumberErr}</h5>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="cardOwner">Card Owner:</label>
                                    <input class="form-control" type="text" id="cardOwner" name="cardOwner"
                                           placeholder="Ex: NGUYEN VAN A" value="${baTmp.cardOwner}" required>
                                    <h5 class="text-danger">${cardOwnerErr}</h5>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="expertDate">Expert date:</label>
                                    <input class="form-control" type="text" id="expertDate" name="expertDate"  
                                           placeholder="MM/DD" value="${baTmp.expertDate}" required>
                                    <h5 class="text-danger">${expertDateErr}</h5>
                                </div>
                                <input type="hidden" name="action" value="linkAccount">
                                <button type="submit" class="btn btn-primary">Submit</button>
                                <a href="home" class="btn btn-warning">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            let bankSelect = document.getElementById('bankSelect');
            bankSelect.addEventListener('change', function () {
                const firstOption = bankSelect.querySelector('option[value="0"]');
                if (firstOption) {
                    firstOption.remove();
                }
            });
        </script>
    </body>
</html>
