<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Including header -->
<jsp:include page="header.jsp"></jsp:include>

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

            <button type="button" class="btn btn-success mb-3" data-toggle="modal" data-target="#addProductModal">
                Add Product
            </button>

            <div class="card-body">

                <!-- Search and Filter Form -->
                <form method="get" action="${pageContext.request.contextPath}/admin/manage-product" class="search-form">
                    <input type="text" name="searchParam" placeholder="Search by name" value="${param.searchParam}" class="form-input">
                    <select name="categoryId" class="form-select">
                        <option value="">All Categories</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.categoryId}" 
                                    <c:if test="${param.categoryId == category.categoryId}">selected</c:if>>
                                ${category.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                    <select name="brandId" class="form-select">
                        <option value="">All Brands</option>
                        <c:forEach var="brand" items="${brands}">
                            <option value="${brand.brandId}" 
                                    <c:if test="${param.brandId == brand.brandId}">selected</c:if>>
                                ${brand.brandName}
                            </option>
                        </c:forEach>
                    </select>
                    <select name="typeId" class="form-select">
                        <option value="">All Type</option>
                        <c:forEach var="type" items="${types}">
                            <option value="${type.typeId}" 
                                    <c:if test="${param.typeId == type.typeId}">selected</c:if>>
                                ${type.typeName}
                            </option>
                        </c:forEach>
                    </select>
                    <select name="isActive" class="form-select">
                        <option value="">All Status</option>
                        <option value="1" <c:if test="${param.isActive == '1'}">selected</c:if>>Active</option>
                        <option value="0" <c:if test="${param.isActive == '0'}">selected</c:if>>Inactive</option>
                        </select>
                        <button type="submit" class="form-button">Search</button>
                    </form>

                    <div class="table-responsive">
                        <table class="table table-bordered" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Code</th>
                                    <th>Name</th>
                                    <th>Image</th>
                                    <th>Category</th>
                                    <th>Brand</th>
                                    <th>Type</th>
                                    <th>Price</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${products}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td><a href="${pageContext.request.contextPath}/admin/product-detail?productId=${product.productId}" />${product.productCode}</td>
                                    <td><a href="${pageContext.request.contextPath}/admin/product-detail?productId=${product.productId}" />${product.productName}</td>
                                    <td><img src="${pageContext.request.contextPath}/${product.image}" alt="${product.productName}" height=70></td>
                                    <td>${product.category.categoryName}</td>
                                    <td>${product.brand.brandName}</td>
                                    <td>${product.type.typeName}</td>
                                    <td>${product.price}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${product.active}">
                                                <span class="text-success">Active</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="text-danger">Inactive</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td style="width: 150px">
                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#editModal${product.productId}">
                                            <i class="fas fa-pen fa-fw"></i>
                                        </button>

                                        <c:choose>
                                            <c:when test="${product.active}">
                                                <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#deactivateModal${product.productId}" data-toggle="tooltip" title="Deactivate Product" data-placement="top">
                                                    <i class="fas fa-ban fa-fw"></i>
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#activateModal${product.productId}"data-toggle="tooltip" title="Activate Product" data-placement="top">
                                                    <i class="fas fa-undo fa-fw"></i>
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                        <button type="button" class="btn btn-success btn-sm" data-toggle="tooltip" title="View detail" data-placement="top">
                                            <a href="${pageContext.request.contextPath}/admin/product-detail?productId=${product.productId}" > <i class="fas fa-eye fa-fw"></i></a>
                                        </button>
                                    </td>
                                </tr>

                                <!-- Edit Modal -->
                            <div class="modal fade" id="editModal${product.productId}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel${product.productId}" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="editModalLabel${product.productId}">Edit Product</h5>
                                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <form method="post" action="${pageContext.request.contextPath}/admin/manage-product" enctype="multipart/form-data" onsubmit="return validateEditForm(${product.productId})">
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <label for="productName${product.productId}">Product Name</label>
                                                    <input type="text" class="form-control" id="productName${product.productId}" name="productName" value="${product.productName}" required>
                                                    <div id="productNameError${product.productId}" class="text-danger"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="productCode${product.productId}">Product Code</label>
                                                    <input type="text" class="form-control" id="productCode${product.productId}" name="productCode" value="${product.productCode}" required>
                                                    <div id="productCodeError${product.productId}" class="text-danger"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label>Current Image</label>
                                                    <div>
                                                        <img src="${pageContext.request.contextPath}/${product.image}" alt="${product.productName}" class="img-fluid" style="max-width: 150px;">
                                                    </div>
                                                </div>

                                                <!-- File input for new image -->
                                                <div class="form-group">
                                                    <label for="image${product.productId}">Change Image</label>
                                                    <input type="file" class="form-control-file" id="image${product.productId}" name="image">
                                                    <input type="hidden" name="currentImage" value="${product.image}"> <!-- Hidden field to keep the current image path -->
                                                </div>
                                                <div class="form-group">
                                                    <label for="categoryId${product.productId}">Category</label>
                                                    <select class="form-control" id="categoryId${product.productId}" name="categoryId" required>
                                                        <c:forEach var="category" items="${categories}">
                                                            <option value="${category.categoryId}" <c:if test="${category.categoryId == product.category.categoryId}">selected</c:if>>
                                                                ${category.categoryName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                    <div id="categoryError${product.productId}" class="text-danger"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="brandId${product.productId}">Brand</label>
                                                    <select class="form-control" id="brandId${product.productId}" name="brandId" required>
                                                        <c:forEach var="brand" items="${brands}">
                                                            <option value="${brand.brandId}" <c:if test="${brand.brandId == product.brand.brandId}">selected</c:if>>
                                                                ${brand.brandName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                    <div id="brandError${product.productId}" class="text-danger"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="typeId${product.productId}">Type</label>
                                                    <select class="form-control" id="typeId${product.productId}" name="typeId" required>
                                                        <c:forEach var="type" items="${types}">
                                                            <option value="${type.typeId}" <c:if test="${type.typeId == product.type.typeId}">selected</c:if>>
                                                                ${type.typeName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                    <div id="typeError${product.productId}" class="text-danger"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="price${product.productId}">Price</label>
                                                    <input type="number" step="0.01" class="form-control" id="price${product.productId}" name="price" value="${product.price}" required>
                                                    <div id="priceError${product.productId}" class="text-danger"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="status${product.productId}">Status</label>
                                                    <select class="form-control" id="status${product.productId}" name="status" required>
                                                        <option value="1" <c:if test="${product.active}">selected</c:if>>Active</option>
                                                        <option value="0" <c:if test="${!product.active}">selected</c:if>>Inactive</option>
                                                        </select>
                                                        <div id="statusError${product.productId}" class="text-danger"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="description${product.productId}">Description</label>
                                                    <textarea type="text" class="form-control" id="description${product.productId}" rows="4" name="description" required>${product.description}</textarea>
                                                    <div id="descriptionError${product.productId}" class="text-danger"></div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                <button type="submit" class="btn btn-primary">Save changes</button>
                                            </div>
                                            <input type="hidden" name="action" value="edit">
                                            <input type="hidden" name="productId" value="${product.productId}">
                                        </form>
                                    </div>
                                </div>
                            </div>

                            <!-- Deactivate Modal -->
                            <div class="modal fade" id="deactivateModal${product.productId}" tabindex="-1" role="dialog" aria-labelledby="deactivateModalLabel${product.productId}" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="deactivateModalLabel${product.productId}">Confirm Deactivation</h5>
                                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Are you sure you want to deactivate this product?
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                            <form method="post" action="${pageContext.request.contextPath}/admin/manage-product">
                                                <input type="hidden" name="productId" value="${product.productId}">
                                                <input type="hidden" name="action" value="deactivate">
                                                <button type="submit" class="btn btn-primary">De-activate</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Activate Modal -->
                            <div class="modal fade" id="activateModal${product.productId}" tabindex="-1" role="dialog" aria-labelledby="activateModalLabel${product.productId}" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="activateModalLabel${product.productId}">Confirm Activation</h5>
                                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Are you sure you want to activate this product?
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                            <form method="post" action="${pageContext.request.contextPath}/admin/manage-product">
                                                <input type="hidden" name="productId" value="${product.productId}">
                                                <input type="hidden" name="action" value="activate">
                                                <button type="submit" class="btn btn-primary">Activate</button>
                                            </form>
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
                            <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                                <a class="page-link" href="?page=${i}&searchParam=${param.searchParam}&categoryId=${param.categoryId}&brandId=${param.brandId}&isActive=${param.isActive}">
                                    ${i}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>

            </div>
        </div>
        <!-- Add Product Modal -->
        <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="addProductModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addProductModalLabel">Add New Product</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="post" action="${pageContext.request.contextPath}/admin/manage-product" onsubmit="return validateForm()"  enctype="multipart/form-data">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="productName">Product Name</label>
                                <input type="text" class="form-control" id="productName" name="productName" required>
                                <input type="hidden" class="form-control" id="action" name="action" value="add">
                                <div id="productNameError" class="text-danger"></div>
                            </div>
                            <div class="form-group">
                                <label for="productCode">Product Code</label>
                                <input type="text" class="form-control" id="productCode" name="productCode" required>
                                <div id="productCodeError" class="text-danger"></div>
                            </div>
                            <div class="form-group">
                                <label for="image">Product Image</label>
                                <input type="file" class="form-control-file" id="image" name="image" required>
                            </div>
                            <div class="form-group">
                                <label for="categoryId">Category</label>
                                <select class="form-control" id="categoryId" name="categoryId" required>
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.categoryId}">${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                                <div id="categoryError" class="text-danger"></div>
                            </div>
                            <div class="form-group">
                                <label for="brandId">Brand</label>
                                <select class="form-control" id="brandId" name="brandId" required>
                                    <c:forEach var="brand" items="${brands}">
                                        <option value="${brand.brandId}">${brand.brandName}</option>
                                    </c:forEach>
                                </select>
                                <div id="brandError" class="text-danger"></div>
                            </div>
                            <div class="form-group">
                                <label for="typeId">Type</label>
                                <select class="form-control" id="typeId" name="typeId" >
                                    <c:forEach var="type" items="${types}">
                                        <option value="${type.typeId}">${type.typeName}</option>
                                    </c:forEach>
                                </select>
                                <div id="typeError" class="text-danger"></div>
                            </div>
                            <div class="form-group">
                                <label for="price">Price</label>
                                <input type="number" step="0.01" class="form-control" id="price" name="price" required>
                                <div id="priceError" class="text-danger"></div>
                            </div>
                            <div class="form-group">
                                <label for="status">Status</label>
                                <select class="form-control" id="status" name="status" required>
                                    <option value="1">Active</option>
                                    <option value="0">Inactive</option>
                                </select>
                                <div id="statusError" class="text-danger"></div>
                            </div>
                            <div class="form-group">
                                <label for="description">Description</label>
                                <textarea type="text" class="form-control" id="description" rows="4" name="description" required></textarea>
                                <div id="descriptionError" class="text-danger"></div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save changes</button>
                        </div>
                    </form>
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
