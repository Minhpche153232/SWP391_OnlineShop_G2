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
            <button type="button" class="btn btn-success mb-3" data-toggle="modal" data-target="#addProductModal">
                Add new brand
            </button>

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
                                    <form method="post" action="${pageContext.request.contextPath}/admin/manage-product" onsubmit="return validateEditForm(${product.productId})">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="brandName${b.brandId}">Brand Name</label>
                                                <input type="text" class="form-control" id="brandName${b.brandId}" name="brandName" value="${b.brandName}" >
                                                <div id="brandNameError${b.brandId}" class="text-danger"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="brandName${b.brandId}">Brand Name</label>
                                                <input type="text" class="form-control" id="brandName${b.brandId}" name="brandName" value="${b.brandName}" >
                                                <div id="brandNameError${b.brandId}" class="text-danger"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="brandName${b.brandId}">Brand Name</label>
                                                <input type="text" class="form-control" id="brandName${b.brandId}" name="brandName" value="${b.brandName}" >
                                                <div id="brandNameError${b.brandId}" class="text-danger"></div>
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
                        <!-- End Edit Modal -->
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</div>