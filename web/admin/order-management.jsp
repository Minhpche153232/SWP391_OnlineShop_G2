
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- Including header -->
<jsp:include page="header.jsp"></jsp:include>
    <script>
        function sendData(status) {
            const text = document.getElementById("textChange");
            if ("pending" === status) {
                text.innerHTML = " Do you want to change status becoming on-going";
            } else if ("on-going" === status) {
                text.innerHTML = "Do you want to change status becoming  success";
            } else
            if ("fail" === status) {
                text.innerHTML = "Do you want to change status becoming fail";
            }
        }

        function sendDataStatus(value, orderId) {

            window.location.href = "/online_shop/changeStatusOrder?status=" + value + "&orderId=" + orderId;
        }
    </script>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
        <jsp:include page="header-content.jsp"></jsp:include>

            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">Product management</h1>

                <!-- Display Notifications -->
            <c:if test="${message eq true}">
                <div class="alert alert-success alert-dismissible fade show" role="alert" style="text-align: center">
                    Change successfully
                    <button type="button" class="btn-danger" data-dismiss="alert" aria-label="Close">x</button>
                </div>
                <%
                    session.removeAttribute("notification");
                %>
            </c:if>
            <c:if test="${message eq false}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert" style="text-align: center">
                    Change fail

                    <button type="button" class="btn-danger" data-dismiss="alert" aria-label="Close">x</button>
                </div>
                <%
                    session.removeAttribute("notificationErr");
                %>
            </c:if>



            <div class="card-body">

                <!-- Search and Filter Form -->
                <form method="get" action="order-manager" class="search-form">
                    <input type="text" name="search" placeholder="Search by name" value="${search}" class="form-input">
                    <select name="status" class="form-select">
                        <option value="pending" 
                                <c:if test="${status == 'pending'}">selected</c:if>>
                                    Pending
                                </option>
                                <option value="on-going" 
                                <c:if test="${status == 'on-going'}">selected</c:if>>
                                    On going
                                </option>
                                <option value="success" 
                                <c:if test="${status == 'success'}">selected</c:if>>
                                    Success
                                </option>
                                <option value="fail" 
                                <c:if test="${status == 'fail'}">selected</c:if>>
                                    Fail
                                </option>
                        </select>

                        <button type="submit" class="form-button">Search</button>
                    </form>

                    <div class="table-responsive">
                        <table class="table table-bordered" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Order ID</th>
                                    <th >Customer</th>
                                    <th>User name</th>
                                    <th >Total</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="i" items="${listOrderDetail}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td><a href="/online_shop/order?userId=${i.user.userId}&status=${i.status}">Order: ${(i.orderId)}</a></td>
                                    <td>${i.user.fullname} </td>

                                    <td>${i.user.userName} </td>

                                    <td><fmt:formatNumber value="${(i.totalPrice)}" type="number" groupingUsed="true" /> VND</td>
                                    <td><!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"  onclick="sendData('${i.status}')">

                                            <c:choose>
                                                <c:when test="${i.status eq 'pending'}">
                                                    <span class="text-success"  style="cursor: pointer">Pending</span>
                                                </c:when>
                                                <c:when test="${i.status eq 'on-going'}">
                                                    <span class="text-success"  style="cursor: pointer">On going</span>
                                                </c:when>
                                                <c:when test="${i.status eq 'success'}">
                                                    <span class="text-success" style="cursor: pointer">Success</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-danger" style="cursor: pointer">Fail</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </button>
                                    </td>


                                </tr>

                                <!-- Modal -->
                            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Change order status</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <span id="textChange"></span>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary" onclick="sendDataStatus('${i.status}', '${i.orderId}')">Save changes</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                        </tbody>
                    </table>



                </div>

                <!-- Pagination -->
                <nav>
                    <ul class="pagination">
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item <c:if test='${i == pageIndex}'>active</c:if>">
                                <a class="page-link" href="?page=${i}&textSearch=${textSearch}&roleSearch=${roleSearch}&activeSearch=${activeSearch}">
                                    ${i}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>

            </div>
        </div>

    </div>
</div>
</div>

<!-- Including footer -->
<jsp:include page="footer.jsp"></jsp:include>

<!-- JavaScript Validation -->
<script>
    function validateForm() {
        let productName = document.getElementById("productName").value.trim();
        let productCode = document.getElementById("productCode").value.trim();
        let price = document.getElementById("price").value;
        let description = document.getElementById("description").value.trim();
        let isValid = true;

        // Clear previous error messages
        document.getElementById("productNameError").innerText = "";
        document.getElementById("productCodeError").innerText = "";
        document.getElementById("priceError").innerText = "";
        document.getElementById("descriptionError").innerText = "";

        if (!productName) {
            document.getElementById("productNameError").innerText = "Product name is required.";
            isValid = false;
        }

        if (!productCode) {
            document.getElementById("productCodeError").innerText = "Product code is required.";
            isValid = false;
        }

        if (price <= 0) {
            document.getElementById("priceError").innerText = "Price must be greater than 0.";
            isValid = false;
        }

        if (!description) {
            document.getElementById("descriptionError").innerText = "Description is required.";
            isValid = false;
        }

        return isValid; // Prevent form submission if validation fails
    }
    function validateEditForm(productId) {
        let productName = document.getElementById("productName" + productId).value.trim();
        let productCode = document.getElementById("productCode" + productId).value.trim();
        let price = document.getElementById("price" + productId).value;
        let description = document.getElementById("description" + productId).value.trim();
        let isValid = true;

        // Clear previous error messages
        document.getElementById("productNameError" + productId).innerText = "";
        document.getElementById("productCodeError" + productId).innerText = "";
        document.getElementById("priceError" + productId).innerText = "";
        document.getElementById("descriptionError" + productId).innerText = "";

        if (!productName) {
            document.getElementById("productNameError" + productId).innerText = "Product name is required.";
            isValid = false;
        }

        if (!productCode) {
            document.getElementById("productCodeError" + productId).innerText = "Product code is required.";
            isValid = false;
        }

        if (price <= 0) {
            document.getElementById("priceError" + productId).innerText = "Price must be greater than 0.";
            isValid = false;
        }

        if (!description) {
            document.getElementById("descriptionError" + productId).innerText = "Description is required.";
            isValid = false;
        }

        return isValid; // Prevent form submission if validation fails
    }

</script>
