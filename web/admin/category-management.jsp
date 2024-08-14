<jsp:include page="header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
        <jsp:include page="header-content.jsp"></jsp:include>
            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">Brand management</h1>

                <!-- Start Notification -->
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
            <!-- End Notification -->

            <!-- Search and Filter Form -->
            <div class="d-flex col-12 justify-content-between">
                <form method="post" action="#" class="search-form">
                    <input type="text" name="txtSearch" placeholder="Search by name" value="${txtSearch}" class="form-input">
                    <input type="hidden" name="service" value="search">
                    <button type="submit" class="form-button">Search</button>
                </form>
                <button type="button" class="btn btn-success mb-3" data-toggle="modal" data-target="#addCategoryModal">
                    Add new category
                </button>
            </div>
            <!--Table content-->
            <div class="table-responsive">
                <table class="table table-bordered" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Category Id</th>
                            <th>Category Name</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listCate}" var="c" varStatus="count">
                            <tr>
                                <th>${count.index+1}</th>
                                <td>${c.categoryId}</td>
                                <td>${c.categoryName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${c.status}">
                                            <span class="text-success">Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-danger">Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#editModal${c.categoryId}">
                                        <i class="fas fa-pen fa-fw"></i>
                                    </button>
                                    <c:choose>
                                        <c:when test="${c.status}">
                                            <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#deactivateModal${c.categoryId}" data-toggle="tooltip" title="Deactivate Category" data-placement="top">
                                                <i class="fas fa-ban fa-fw"></i>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#activateModal${c.categoryId}"data-toggle="tooltip" title="Activate Category" data-placement="top">
                                                <i class="fas fa-undo fa-fw"></i>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <!-- Edit Modal -->
                        <div class="modal fade" id="editModal${c.categoryId}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel${c.categoryId}" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editModalLabel${c.categoryId}">Edit Category</h5>
                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <form method="post" action="category" onsubmit="return validateEditForm(${c.categoryId})">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="categoryId${c.categoryId}">Category Id</label>
                                                <input type="text" class="form-control" id="categoryId${c.categoryId}" name="categoryId" value="${c.categoryId}" readonly>
                                                <div id="categoryIdError${c.categoryId}" class="text-danger"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="categoryName${c.categoryId}">Category Name</label>
                                                <input type="text" class="form-control" id="categoryName${b.categoryId}" name="categoryName" value="${c.categoryName}" required>
                                                <div id="categoryNameError${c.categoryId}" class="text-danger"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="description${c.categoryId}">Description</label>
                                                <input type="text" class="form-control" id="description${c.categoryId}" name="description" value="${c.description}" required>
                                                <div id="descriptionError${c.categoryId}" class="text-danger"></div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                        </div>
                                        <input type="hidden" name="service" value="update">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- End Edit Modal -->

                        <!--Add new brand modal-->
                        <div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addCategoryModal">Edit Brand</h5>
                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <form method="post" action="category" onsubmit="return validateAddForm()">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="categoryName">Category Name</label>
                                                <input type="text" class="form-control" id="categoryName" name="categoryName" required>
                                                <div id="categoryNameError" class="text-danger"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="description" class="form-label">Description</label>
                                                <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                        </div>
                                        <input type="hidden" name="service" value="add">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!--End Add new brand modal-->

                        <!--Activate Brand-->
                        <div class="modal fade" id="activateModal${b.brandId}" tabindex="-1" role="dialog" aria-labelledby="activateModalLabel${b.brandId}" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="activateModalLabel${b.brandId}">Confirm Activation</h5>
                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Are you sure you want to activate this product?
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                        <a class="btn btn-danger" href="brand?service=delete&bId=${c.brandId}&status=${c.status}">
                                            ${c.status == true ? "Inactive" : "Active"}</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Deactivate Brand-->
                        <div class="modal fade" id="deactivateModal${c.categoryId}" tabindex="-1" role="dialog" aria-labelledby="deactivateModalLabel${product.productId}" aria-hidden="true">
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
                                        <a class="btn btn-danger" href="brand?service=delete&bId=${b.brandId}&status=${b.status}">
                                            ${b.status == true ? "Inactive" : "Active"}</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </div>
</div>
<script>
    function validateAddForm() {
        let categoryName = document.getElementById("categoryName").value.trim();
        let description = document.getElementById("description").value.trim();
        let isValid = true;

        // Clear previous error messages
        document.getElementById("categoryNameError").innerText = "";
        document.getElementById("descriptionError").innerText = "";

        if (!categoryName) {
            document.getElementById("categoryNameError").innerText = "Category name is required.";
            isValid = false;
        }
        if (!description) {
            document.getElementById("descriptionError").innerText = "Description is required.";
            isValid = false;
        }

        return isValid; // Prevent form submission if validation fails
    }
    function validateEditForm(categoryId) {
        let categoryName = document.getElementById("categoryName" + categoryId).value.trim();
        let description = document.getElementById("description" + categoryId).value.trim();
        let isValid = true;

        // Clear previous error messages
        document.getElementById("categoryNameError" + categoryId).innerText = "";
        document.getElementById("descriptionError" + categoryId).innerText = "";

        if (!categoryName) {
            document.getElementById("categoryNameError" + categoryId).innerText = "Category name is required.";
            isValid = false;
        }
        if (!description) {
            document.getElementById("descriptionError" + categoryId).innerText = "Description is required.";
            isValid = false;
        }

        return isValid; // Prevent form submission if validation fails
    }

</script>