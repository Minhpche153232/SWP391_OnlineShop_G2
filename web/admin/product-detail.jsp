<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Including header -->
<jsp:include page="header.jsp"></jsp:include>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
        <jsp:include page="header-content.jsp"></jsp:include>

            <div class="container-fluid">
                <h1>${product.productName}</h1>
            <div class="product-info">
                <p><strong>Code:</strong> ${product.productCode}</p>
                <p><strong>Category:</strong> ${product.category.categoryName}</p>
                <p><strong>Brand:</strong> ${product.brand.brandName}</p>
                <p><strong>Price:</strong> $${product.price}</p>
                <p><strong>Status:</strong> 
                    <c:choose>
                        <c:when test="${product.active}">Available</c:when>
                        <c:otherwise>Out of Stock</c:otherwise>
                    </c:choose>
                </p>
                <p><strong>Description:</strong> ${product.description}</p>
            </div>
            <div class="product-details">
                <h2>Available Options</h2> 
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

                <!-- Add/Edit Product Detail Button -->
                <button class="btn btn-primary" data-toggle="modal" data-target="#productDetailModal" style="margin-bottom: 20px">
                    Add Product Detail
                </button>

                <table class="table" id="dataTable">
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Size</th>
                            <th>Color</th>
                            <th>Units in Stock</th>
                            <th>Discount</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detail" items="${product.productDetails}">
                            <tr>

                                <td><img style="max-width: 80px" src="${pageContext.request.contextPath}/${detail.image}" alt="${product.productName}"/></td>
                                <td>${detail.size}</td>
                                <td>${detail.color}</td>
                                <td>${detail.unitInStock}</td>
                                <td>${detail.discount}%

                                    <button class="btn btn-warning btn-sm" data-toggle="modal" data-target="#discountModal${detail.size}${detail.color}">
                                        <i class="fas fa-pen fa-fw"></i>
                                    </button>
                                    <div class="modal fade" id="discountModal${detail.size}${detail.color}" tabindex="-1" role="dialog" aria-labelledby="discountModalLabel${detail.size}${detail.color}" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="deleteModalLabel${detail.size}${detail.color}">Update Discount</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <form method="post" action="${pageContext.request.contextPath}/admin/product-detail">
                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <label for="size">Discount</label>
                                                            <input type="number" class="form-control"value="${detail.discount}" id="discount" min="0" max="100" name="discount" required>
                                                            <div id="sizeError" class="text-danger"></div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                        <input type="hidden" name="productId" value="${product.productId}">
                                                        <input type="hidden" class="form-control" name="action" value="update-discount" >
                                                        <input type="hidden" name="size" value="${detail.size}">
                                                        <input type="hidden" name="color" value="${detail.color}">
                                                        <button type="submit" class="btn btn-danger">Update</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                </td>
                                <td> 
                                    <button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteModal${detail.size}${detail.color}">
                                        <i class="fas fa-trash fa-fw"></i>
                                    </button>
                                    <div class="modal fade" id="deleteModal${detail.size}${detail.color}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel${detail.size}${detail.color}" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="deleteModalLabel${detail.size}${detail.color}">Delete Product Detail</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    Are you sure you want to delete this product detail?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                    <form method="post" action="${pageContext.request.contextPath}/admin/product-detail">
                                                        <input type="hidden" name="productId" value="${product.productId}">
                                                        <input type="hidden" class="form-control" name="action" value="delete" >
                                                        <input type="hidden" name="size" value="${detail.size}">
                                                        <input type="hidden" name="color" value="${detail.color}">
                                                        <button type="submit" class="btn btn-danger">Delete</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div style="padding-bottom: 20px">

                    <button  class="btn btn-info" onclick="window.location.href = '${pageContext.request.contextPath}/admin/manage-product'">Back</button>
                </div>
            </div>

            <!-- Product Detail Modal -->
            <div class="modal fade" id="productDetailModal" tabindex="-1" role="dialog" aria-labelledby="productDetailModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="productDetailModalLabel">Update <strong> ${product.productName}</strong></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form method="post" action="${pageContext.request.contextPath}/admin/product-detail" enctype="multipart/form-data" onsubmit="return validateProductDetailForm()">
                            <div class="modal-body">
                                <!-- Existing form fields -->
                                <div class="form-group">
                                    <label for="size">Size</label>
                                    <input type="number" class="form-control" id="size" name="size" min="35" max="50" required>
                                    <input type="hidden" class="form-control" name="action" value="add" >
                                    <div id="sizeError" class="text-danger"></div>
                                </div>
                                <div class="form-group">
                                    <label for="color">Color</label>
                                    <input type="text" class="form-control" id="color" name="color" required>
                                    <div id="colorError" class="text-danger"></div>
                                </div>
                                <div class="form-group">
                                    <label for="unitInStock">Units in Stock</label>
                                    <input type="number" class="form-control" id="unitInStock" name="unitInStock" required>
                                    <div id="unitInStockError" class="text-danger"></div>
                                </div>
                                <div class="form-group">
                                    <label for="discount">Discount</label>
                                    <input type="number" class="form-control" id="discount" name="discount" min="0" max="100" required>
                                    <div id="discountError" class="text-danger"></div>
                                </div>
                                <div class="form-group">
                                    <label for="image">Product Image</label>
                                    <input type="file" class="form-control-file" id="image" name="image" required>
                                </div>
                                <input type="hidden" name="productId" value="${product.productId}">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function validateProductDetailForm() {
        let size = document.getElementById("size").value;
        let discount = document.getElementById("discount").value;
        let color = document.getElementById("color").value.trim();
        let unitInStock = document.getElementById("unitInStock").value;
        let isValid = true;

        // Clear previous error messages
        document.getElementById("sizeError").innerText = "";
        document.getElementById("colorError").innerText = "";
        document.getElementById("unitInStockError").innerText = "";
        document.getElementById("discountError").innerText = "";

        // Validate size > 0
        if (size < 35 || size > 50) {
            document.getElementById("sizeError").innerText = "Size must be in range 35-50.";
            isValid = false;
        }
        if (discount < 0 || discount > 100) {
            document.getElementById("discountError").innerText = "Discount must be in range 0-100.";
            isValid = false;
        }

        // Validate color (alphabetic only)
        let colorRegex = /^[a-zA-Z]+$/;
        if (!colorRegex.test(color)) {
            document.getElementById("colorError").innerText = "Color must contain only letters (a-z, A-Z).";
            isValid = false;
        }

        // Validate unit in stock > 0
        if (unitInStock <= 0) {
            document.getElementById("unitInStockError").innerText = "Units in stock must be greater than 0.";
            isValid = false;
        }

        return isValid;
    }

</script>

<jsp:include page="footer.jsp"></jsp:include>
