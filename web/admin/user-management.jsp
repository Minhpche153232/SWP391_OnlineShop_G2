
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Including header -->
<jsp:include page="header.jsp"></jsp:include>
    <script>
        function sendData(userId, type) {
            const selectElement = document.getElementById(`roleSelect_` + userId);
            const selectedValue = selectElement.value;
            window.location.href = "ChangeUserController?value=" + selectedValue + "&userId=" + userId + "&type=" + type;
        }

        function sendDataStatus(value, userId, type) {
            window.location.href = "ChangeUserController?value=" + value + "&userId=" + userId + "&type=" + type;
        }
    </script>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
        <jsp:include page="header-content.jsp"></jsp:include>

            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">Product management</h1>

                <!-- Display Notifications -->
            <c:if test="${not empty sessionScope.notification}">
                <div class="alert alert-success alert-dismissible fade show" role="alert" style="text-align: center">
                    ${sessionScope.notification}
                    <button type="button" class="btn-danger" data-dismiss="alert" aria-label="Close">x</button>
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

            <c:if test="${message ne null}">
                <i style="color: blue">${message}</i>
            </c:if>

            <div class="card-body">

                <!-- Search and Filter Form -->
                <form method="get" action="user-manager" class="search-form">
                    <input type="text" name="textSearch" placeholder="Search by name" value="${textSearch}" class="form-input">
                    <select name="roleSearch" class="form-select">
                        <option value="">All role</option>
                        <c:forEach var="r" items="${listRole}">
                            <option value="${r.roleId}" 
                                    <c:if test="${roleSearch == r.roleId}">selected</c:if>>
                                ${r.roleName}
                            </option>
                        </c:forEach>
                    </select>

                    <select name="activeSearch" class="form-select">
                        <option value="">All Status</option>
                        <option value="1" <c:if test="${activeSearch == '1'}">selected</c:if>>Active</option>
                        <option value="0" <c:if test="${activeSearch == '0'}">selected</c:if>>Inactive</option>
                        </select>
                        <button type="submit" class="form-button">Search</button>
                    </form>

                    <div class="table-responsive">
                        <table class="table table-bordered" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>ID</th>
                                    <th>Full name</th>
                                    <th>Address</th>
                                    <th>Phone</th>
                                    <th>Email</th>
                                    <th>User name</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${listUser}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td><a href="${pageContext.request.contextPath}/admin/product-detail?productId=${user.userId}" />${user.userId}</td>
                                    <td><a href="${pageContext.request.contextPath}/admin/product-detail?productId=${user.userId}" />${user.fullname}</td>
                                    <td>${user.address}</td>
                                    <td>${user.phone}</td>
                                    <td>${user.email}</td>
                                    <td>${user.userName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.status eq true}">
                                                <span class="text-success" onclick="sendDataStatus(false, '${user.userId}', 'status')">Active</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="text-danger" onclick="sendDataStatus(true, '${user.userId}', 'status')">Inactive</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                </tr>



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
