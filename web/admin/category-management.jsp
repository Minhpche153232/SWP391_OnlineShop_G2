<jsp:include page="header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
        <jsp:include page="header-content.jsp"></jsp:include>
            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">Category management</h1>

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
                <form method="post" action="category" class="search-form">
                    <input type="text" name="txtSearch" placeholder="Search by name" value="${txtSearch}" class="form-input">
                    <input type="hidden" name="service" value="search">
                    <button class="form-button" type="submit">Search</button>
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
                        <c:forEach items="${listCate}" var="cate" varStatus="count">
                            <tr>
                                <th>${count.index+1}</th>
                                <td>${cate.categoryId}</td>
                                <td>${cate.categoryName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${cate.status}">
                                            <span class="text-success">Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-danger">Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#editModal${cate.categoryId}">
                                        <i class="fas fa-pen fa-fw"></i>
                                    </button>
                                    <c:choose>
                                        <c:when test="${cate.status}">
                                            <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#deactivateModal${cate.categoryId}" data-toggle="tooltip" title="Deactivate Category" data-placement="top">
                                                <i class="fas fa-ban fa-fw"></i>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#activateModal${cate.categoryId}"data-toggle="tooltip" title="Activate Category" data-placement="top">
                                                <i class="fas fa-undo fa-fw"></i>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <!-- Edit Modal -->
                        <div class="modal fade" id="editModal${cate.categoryId}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel${cate.categoryId}" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editModalLabel${cate.categoryId}">Edit Category</h5>
                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <form method="post" action="category" onsubmit="return validateEditForm(${cate.categoryId})">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="categoryId${cate.categoryId}">Category Id:</label>
                                                <input type="text" class="form-control" id="categoryId${cate.categoryId}" name="categoryId" value="${cate.categoryId}" readonly>
                                                <div id="categoryIdError${cate.categoryId}" class="text-danger"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="categoryName${cate.categoryId}">Category Name:</label>
                                                <input type="text" class="form-control" id="categoryName${cate.categoryId}" name="categoryName" value="${cate.categoryName}" required>
                                                <div id="categoryNameError${cate.categoryId}" class="text-danger"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="categoryDescription${cate.categoryId}">Description:</label>
                                                <input type="text" class="form-control" id="categoryDescription${cate.categoryId}" name="categoryDescription" value="${cate.description}" required>
                                                <div id="categoryDescriptionError${cate.categoryId}" class="text-danger"></div>
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

                        <!--Add new category modal-->
                        <div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addCategoryModal">Add new category</h5>
                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <form method="post" action="category" onsubmit="return validateAddForm()">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="categoryName">Category Name:</label>
                                                <input type="text" class="form-control" id="categoryName" name="categoryName" required>
                                                <div id="categoryNameError" class="text-danger"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="categoryDescription">Description:</label>
                                                <input type="text" class="form-control" id="categoryDescription" name="categoryDescription" required>
                                                <div id="categoryDescriptionError" class="text-danger"></div>
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
                        <!--End Add new category modal-->

                        <!--Activate Category-->
                        <div class="modal fade" id="activateModal${cate.categoryId}" tabindex="-1" role="dialog" aria-labelledby="activateModalLabel${cate.categoryId}" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="activateModalLabel${cate.categoryId}">Confirm Activation</h5>
                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Are you sure you want to activate this category?
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                        <a class="btn btn-danger" href="category?service=delete&cateId=${cate.categoryId}&status=${cate.status}">
                                            ${cate.status == true ? "Inactive" : "Active"}</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Deactivate category-->
                        <div class="modal fade" id="deactivateModal${cate.categoryId}" tabindex="-1" role="dialog" aria-labelledby="deactivateModalLabel${cate.categoryId}" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="deactivateModalLabel${cate.categoryId}">Confirm Deactivation</h5>
                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Are you sure you want to deactivate this category?
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                        <a class="btn btn-danger" href="category?service=delete&cateId=${cate.categoryId}&status=${cate.status}">
                                            ${cate.status == true ? "Inactive" : "Active"}</a>
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
        let categoryDescription = document.getElementById("categoryDescription").value.trim();
        let isValid = true;

        // Clear previous error messages
        document.getElementById("categoryNameError").innerText = "";
        document.getElementById("categoryDescriptionError").innerText = "";

        if (!categoryName) {
            document.getElementById("categoryNameError").innerText = "Category name is required.";
            isValid = false;
        }
        if (!categoryDescription) {
            document.getElementById("categoryDescriptionError").innerText = "Description is required";
            isValid = false;
        }

        return isValid;// Prevent form submission if validation fails
    }
    function validateEditForm(categoryId) {
        let categoryName = document.getElementById("categoryName" + categoryId).value.trim();
        let categoryDescription = document.getElementById("categoryDescription" + categoryId).value.trim();
        let isValid = true;

        // Clear previous error messages
        document.getElementById("categoryNameError" + categoryId).innerText = "";
        document.getElementById("categoryDescriptionError" + categoryId).innerText = "";

        if (!categoryName) {
            document.getElementById("categoryNameError" + categoryId).innerText = "Category name is required.";
            isValid = false;
        }
        if (!categoryDescription) {
            document.getElementById("categoryDescriptionError" + categoryId).innerText = "Description is required";
            isValid = false;
        }

        return isValid; // Prevent form submission if validation fails
    }

</script>