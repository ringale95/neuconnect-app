<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed List</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="module" src="${pageContext.request.contextPath}/js/index.js"></script>


    <style>
        body {
            background-color: rgb(245, 245, 238);
        }
    </style>
</head>

<body>
    <cm:header />
    <div class="container mt-4">
        <div class="row">
            <div class="col" id="user-details-container">
                <div class="card text-center">
                    <div class="mt-4">
                        <img src="${pageContext.request.contextPath}${user.profilepicPath}" class="rounded-circle"
                            style="width: 100px; height: 100px;">
                    </div>
                    <div class="card-body" style="height: 15rem;">

                        <h5 class="card-title mt-2"><a
                                href="/user-dashboard/user-profile/${user.id}">${user.fname}&nbsp;${user.lname}</a></h5>
                        <p class="card-text">${user.aboutMe}</p>
                        <p>Karma: <strong>${user.karma}</strong> points<br>Access Level: <strong>${user.role}</strong>
                        </p>
                    </div>
                </div>
                <div></div>
            </div>
            <div class="col-5">
                <div class="d-flex position-relative mt-2 mb-1 bg-white p-4 rounded-4 border">
                    <img src="${pageContext.request.contextPath}${user.profilepicPath}" class="rounded-circle"
                        style="width: 60px; height: 60px;" alt="...">

                    <div style="width: 80%;">
                        <a id="create-post" href="/create-post" class="btn btn-secondary m-3"
                            style="width: 100%; height: 60%; border-radius: 20px; background-color: rgb(255, 246, 246); color: rgb(0 0 0 / 75%)">Start
                            a
                            post</a>
                    </div>
                </div>
                <hr>
                <div id="feed-container">
                </div>
                <button id="load-more" class="btn btn-secondary m-4">Load More</button>
            </div>
            <div class="col" id="summary-container">
                <div class="card">
                    <div class="card-body p-4" style="height: 8rem;">
                        <h6 class="card-title">Filters</h6>
                        <div class="card-text input-group">
                            <input id="search-field" type="text" class="form-control search-bar mt-2"
                                placeholder="Search here" style="width: 100%">
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>