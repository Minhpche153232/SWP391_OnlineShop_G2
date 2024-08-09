<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Including header -->
<jsp:include page="header.jsp"></jsp:include>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">

            <!-- Including header content -->
        <jsp:include page="header-content.jsp"></jsp:include>

            <!-- Task management -->
            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">Product management</h1>
                <div class="card-body">
                    <div class="table-responsive">

                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Code</th>
                                    <th>Name</th>
                                    <th>Category</th>
                                    <th>Brand</th>
                                    <th>Image</th>
                                    <th>Price</th>
                                    <th>Color</th>
                                    <th>Size</th>
                                    <th>Unit in stock</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="t" items="${task}" varStatus="status">
                                <tr>
                                    <td></td>
                                </tr>

                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<!-- Including footer -->
<jsp:include page="footer.jsp"></jsp:include>
