<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Users</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container-body {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
    </style>
    <script type="module" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
   <cm:header/>
    <div class="container mt-4">
        <h2>Manage Users</h2>
        <div class="container-body">
            <div class="input-group mb-3 mt-3">
                        <input type="text" id = "search-field" class="form-control" placeholder="Search..." aria-label="Search">
                        <button id = "search-button" class="btn btn-primary" type="button">Search</button>
                    </div>
                    <table class="table table-hover table-bordered mt-3 text-center">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.fname} ${user.lname}</td>
                                    <td>${user.username}</td>
                                    <td>${user.role.name()}</td> <!-- Access the role enum's name -->
                                    <td>
                                        <a href="/user-dashboard/manage-users/${user.id}/edit" class="btn btn-primary btn-sm">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="dropdown mt-3">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                            Filter by Role
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <li><a class="dropdown-item" href="#">Admin</a></li>
                            <li><a class="dropdown-item" href="#">Authority</a></li>
                            <li><a class="dropdown-item" href="#">User</a></li>
                        </ul>
                        <button class="btn btn-link">&lt; Previous </button>
                        <button class="btn btn-link">Next &gt; </button>
                    </div>
        </div>

    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Bootstrap Icons -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.js"></script>
</body>
</html>
