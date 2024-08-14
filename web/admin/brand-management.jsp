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
                <form method="post" action="brand" class="search-form">
                    <input type="text" name="txtSearch" placeholder="Search by name" value="${txtSearch}" class="form-input">
                    <input type="hidden" name="service" value="search">
                    <button type="submit" class="form-button">Search</button>
                </form>
                <button type="button" class="btn btn-success mb-3" data-toggle="modal" data-target="#addBrandModal">
                    Add new brand
                </button>
            </div>
            <!--Table content-->
            <div class="table-responsive">
                <table class="table table-bordered" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Brand Id</th>
                            <th>Brand Name</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listB}" var="b" varStatus="count">
                            <tr>
                                <th>${count.index+1}</th>
                                <td>${b.brandId}</td>
                                <td>${b.brandName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${b.status}">
                                            <span class="text-success">Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-danger">Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#editModal${b.brandId}">
                                        <i class="fas fa-pen fa-fw"></i>
                                    </button>
                                    <c:choose>
                                        <c:when test="${b.status}">
                                            <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#deactivateModal${b.brandId}" data-toggle="tooltip" title="Deactivate Brand" data-placement="top">
                                                <i class="fas fa-ban fa-fw"></i>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#activateModal${b.brandId}"data-toggle="tooltip" title="Activate Brand" data-placement="top">
                                                <i class="fas fa-undo fa-fw"></i>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                            <!-- Edit Modal -->
                        <div class="modal fade" id="editModal${b.brandId}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel${b.brandId}" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editModalLabel${b.brandId}">Edit Brand</h5>
                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <form method="post" action="brand" onsubmit="return validateEditForm(${b.brandId})">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="brandId${b.brandId}">Brand Id</label>
                                                <input type="text" class="form-control" id="brandId${b.brandId}" name="brandId" value="${b.brandId}" readonly>
                                                <div id="brandIdError${b.brandId}" class="text-danger"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="brandName${b.brandId}">Brand Name</label>
                                                <input type="text" class="form-control" id="brandName${b.brandId}" name="brandName" value="${b.brandName}" required>
                                                <div id="brandNameError${b.brandId}" class="text-danger"></div>
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
                        <div class="modal fade" id="addBrandModal" tabindex="-1" role="dialog" aria-labelledby="addBrandModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addBrandModal">Edit Brand</h5>
                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <form method="post" action="brand" onsubmit="return validateAddForm()">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="brandName">Brand Name</label>
                                                <input type="text" class="form-control" id="brandName" name="brandName" required>
                                                <div id="brandNameError" class="text-danger"></div>
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
                                        <a class="btn btn-danger" href="brand?service=delete&bId=${b.brandId}&status=${b.status}">
                                            ${b.status == true ? "Inactive" : "Active"}</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Deactivate Brand-->
                        <div class="modal fade" id="deactivateModal${b.brandId}" tabindex="-1" role="dialog" aria-labelledby="deactivateModalLabel${product.productId}" aria-hidden="true">
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
        let brandName = document.getElementById("brandName").value.trim();
        let isValid = true;

        // Clear previous error messages
        document.getElementById("brandNameError").innerText = "";

        if (!brandName) {
            document.getElementById("brandNameError").innerText = "Brand name is required.";
            isValid = false;
        }

        return isValid; // Prevent form submission if validation fails
    }
    function validateEditForm(brandId) {
        let brandName = document.getElementById("brandName" + brandId).value.trim();
        let isValid = true;

        // Clear previous error messages
        document.getElementById("brandNameError" + brandId).innerText = "";

        if (!brandName) {
            document.getElementById("brandNameError" + brandId).innerText = "Brand name is required.";
            isValid = false;
        }

        return isValid; // Prevent form submission if validation fails
    }

</script>