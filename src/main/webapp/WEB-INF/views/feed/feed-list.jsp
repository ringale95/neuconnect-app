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
                        <h5 class="card-title mt-2">${user.fname}&nbsp;${user.lname}</h5>
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
            </div>
            <div class="col" id="summary-container">
                <div class="card">
                    <div class="card-body p-4" style="height: 13rem;">
                        <h6 class="card-title">Filters</h6>
                        <div class="card-text input-group">
                            <input type="text" class="form-control search-bar mt-2" placeholder="Search here"
                                style="width: 100%">
                            <h6 class="mt-3">Sort by:</h6>
                        </div>
                        <div class="mt-2">
                            <span class="m-2" id="hot">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                    class="bi bi-fire" viewBox="0 0 16 16">
                                    <path
                                        d="M8 16c3.314 0 6-2 6-5.5 0-1.5-.5-4-2.5-6 .25 1.5-1.25 2-1.25 2C11 4 9 .5 6 0c.357 2 .5 4-2 6-1.25 1-2 2.729-2 4.5C2 14 4.686 16 8 16m0-1c-1.657 0-3-1-3-2.75 0-.75.25-2 1.25-3C6.125 10 7 10.5 7 10.5c-.375-1.25.5-3.25 2-3.5-.179 1-.25 2 1 3 .625.5 1 1.364 1 2.25C11 14 9.657 15 8 15" />
                                </svg>
                                Hot</span>
                            <span id="latest">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                    class="bi bi-stars" viewBox="0 0 16 16">
                                    <path
                                        d="M7.657 6.247c.11-.33.576-.33.686 0l.645 1.937a2.89 2.89 0 0 0 1.829 1.828l1.936.645c.33.11.33.576 0 .686l-1.937.645a2.89 2.89 0 0 0-1.828 1.829l-.645 1.936a.361.361 0 0 1-.686 0l-.645-1.937a2.89 2.89 0 0 0-1.828-1.828l-1.937-.645a.361.361 0 0 1 0-.686l1.937-.645a2.89 2.89 0 0 0 1.828-1.828zM3.794 1.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387A1.73 1.73 0 0 0 4.593 5.69l-.387 1.162a.217.217 0 0 1-.412 0L3.407 5.69A1.73 1.73 0 0 0 2.31 4.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387A1.73 1.73 0 0 0 3.407 2.31zM10.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.16 1.16 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.16 1.16 0 0 0-.732-.732L9.1 2.137a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732z" />
                                </svg>New</span>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>