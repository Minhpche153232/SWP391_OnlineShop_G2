window.onload = function () {
    var currentURI = window.location.pathname;

    var homeDiv = document.getElementById("home");
    var shopDiv = document.getElementById("shop");
    var shopInputDiv = document.getElementById("input-search-shop");


    if (currentURI.includes("/shop")) {
        shopDiv.classList.add("nav-item", "active");
        homeDiv.classList.remove("nav-item", "active");
        shopInputDiv.classList.add("hidden");
    } else {
        homeDiv.classList.add("nav-item", "active");
        shopDiv.classList.remove("nav-item", "active");
        shopInputDiv.classList.remove("hidden");

    }
};