<%-- 
    Document   : brand-list
    Created on : Aug 12, 2024, 6:07:30 AM
    Author     : catmi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Brand List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Home</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="dashboard">Dashboard</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <div class="container-fluid col-12 pt-3">
            <!-- Content -->
            <div class="col-12 justify-content-between d-flex">
                <form class="col-6 d-flex" action="" method="post">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
                <a class="col-2 btn btn-success" href="add-brand.jsp">
                    <i class=""></i>Add new brand
                </a>
            </div>
            <div class="pt-3">
                <h3>List/<span> Brand</span></h3>
            </div>
            <table class="text-center table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Status</th>
                        <th scope="col" colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listB}" var="b" varStatus="count">
                        <tr>
                            <th>${count.index+1}</th>
                            <td>${b.brandId}</td>
                            <td>${b.brandName}</td>
                            <td>${b.status == true ? "Active" : "Inactive"}</td>
                            <td><a class="btn btn-success" href="brand?service=details&bId=${b.brandId}">Update</a></td>
                            <td><a class="btn btn-danger" href="brand?service=delete&bId=${b.brandId}&status=${b.status}">
                                    ${b.status == true ? "Inactive" : "Active"}</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
