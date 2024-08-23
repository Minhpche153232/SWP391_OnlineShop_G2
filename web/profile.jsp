<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">


        <title>profile edit data and skills - Bootdey.com</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="css/tiny-slider.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/my-dropdown.css" rel="stylesheet">
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
        <link href="css/input.css" rel="stylesheet">

        <style type="text/css">
            body{
                background: #f7f7ff;
                margin-top:20px;
            }
            .card {
                position: relative;
                display: flex;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 0 solid transparent;
                border-radius: .25rem;
                margin-bottom: 1.5rem;
                box-shadow: 0 2px 6px 0 rgb(218 218 253 / 65%), 0 2px 6px 0 rgb(206 206 238 / 54%);
            }
            .me-2 {
                margin-right: .5rem!important;
            }

        </style>
        <script>
            function change() {
                let file = document.getElementById("imageUpload").value;
                console.log(file)
                if (file !== null) {
                    const avatarChange = document.getElementById("avatarChange");
                    avatarChange.src = URL.createObjectURL(document.getElementById("imageUpload").files[0]);
                }
            }
        </script>
    </head>
    <body> 
        <%@include file="top-bar.jsp" %>
        <form action="profile" method="POST">
            <div class="container" style="margin-top: 50px">
                <div class="main-body">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="card">
                                <div class="card-body">
                                    <div class="d-flex flex-column align-items-center text-center">
                                        <label for="imageUpload">
                                            <img id="avatarChange" src=${sessionScope.currentUser.avatar ne null   ?
                                                                         sessionScope.currentUser.avatar :
                                                                         "https://bootdey.com/img/Content/avatar/avatar6.png"} 
                                                 alt="Admin" class="rounded-circle p-1 bg-primary" width="110" style="cursor: pointer">
                                        </label>
                                        <input type="file" name="avatar" id="imageUpload" accept="image/*" style="display: none" 
                                               onchange="(function () {
                                                           let fileInput = document.getElementById('imageUpload');
                                                           const file = fileInput.files[0];
                                                           console.log(file);

                                                           if (file.name !== null) {
                                                               const avatarChange = document.getElementById('avatarChange');
                                                               avatarChange.src = 'images/' + file.name;
                                                           }
                                                       })()">
                                        <div class="mt-3">
                                            <h4>${sessionScope.currentUser.fullname}</h4>
                                            <p class="text-secondary mb-1">${sessionScope.currentUser.email}</p>

                                        </div>
                                    </div>
                                    <hr class="my-4">
                                    <ul class="list-group list-group-flush">
                                        <c:if test="${sessionScope.currentUser.role eq '1'}">
                                            <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                                <h6 class="mb-0">
                                                    <a class="nav-link" href="admin/dashboard"><img src="images/icon-list.svg" width="24"/>
                                                        <span class="text-secondary">Dashboard</span></a></h6>
                                            </li>
                                           
                                        </c:if>
                                        <c:if test="${sessionScope.currentUser.role eq '3'}">
                                            <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                                <h6 class="mb-0" ><a class="nav-link" href="order" style="color: #A00000">
                                                        <i class="fa-solid fa-cart-shopping"></i>
                                                        <span class="text-secondary"> My order</span></a></h6>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-8" style="margin-bottom: 50px">

                            <c:if test="${message ne null}">
                                <i style="color: blue">${message}</i>
                            </c:if>

                            <div class="card">
                                <div class="card-body">
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">User name</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" class="form-control" readonly name="userName"  value=${sessionScope.currentUser.userName}>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Full Name</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" class="form-control" name="fullName"  value="${sessionScope.currentUser.fullname}">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Email</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" class="form-control" readonly name="email"  value="${sessionScope.currentUser.email}">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Phone</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" class="form-control" readonly name="phone"  value=${sessionScope.currentUser.phone}>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Address</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" class="form-control"name="address"  value="${sessionScope.currentUser.address}">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">DOB</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <input type="text" class="form-control" name="dob" value=${sessionScope.currentUser.dob}>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Balance</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                           <h6><fmt:formatNumber value="${sessionScope.currentUser.balance}" type="number" groupingUsed="true" /> VND</h6>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Gender</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="gender" id="flexCheckDefault"
                                                       value="female" ${sessionScope.currentUser.gender eq false ? 'checked' : ''}>
                                                <label class="form-check-label" for="flexCheckDefault">
                                                    Female
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="gender" id="flexCheckChecked"
                                                       value="male" ${sessionScope.currentUser.gender eq true ? 'checked' : ''}>
                                                <label class="form-check-label" for="flexCheckChecked">
                                                    Male
                                                </label>
                                            </div>
                                        </div>


                                    </div>


                                    <div class="row">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9 text-secondary">
                                            <button type="submit" class="btn btn-primary px-4">Save Changes</button>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>

                    </div>
                </div>
            </div>
        </form>
        <%@include file="change-password.jsp" %>
        <div style="margin-bottom: 100px"></div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

        </script>

        <%@include file="footer.jsp" %>

    </body>
</html>