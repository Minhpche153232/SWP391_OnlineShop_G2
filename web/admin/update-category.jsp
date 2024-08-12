<%-- 
    Document   : update-category
    Created on : Aug 12, 2024, 6:08:12 AM
    Author     : catmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <form action="category" method="post">
                <h3 class="text-danger">${mess}</h3>
                <div class="mb-3">
                    <label for="cateId" class="form-label">Category Id:</label>
                    <input type="text" class="form-control" name="cateId" id="cateId" readonly value="${cate.categoryId}">
                </div>
                <div class="mb-3">
                    <label for="cateName" class="form-label">Category Name:</label>
                    <input class="form-control" id="cateName" name="cateName" value="${cate.categoryName}">
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="3">${cate.description}</textarea>
                </div>
                <input type="hidden" name="service" value="update">
                <button class="btn btn-success" type="submit">Update</button>
            </form>
        </div>
    </body>
</html>
