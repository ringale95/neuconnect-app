<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .profile-card {
            border: none;
            border-radius: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .profile-picture {
            border-radius: 10%;
        }

        .bio-card {
            border: none;
            border-radius: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>

<body>
    <cm:header />
    <div class="container mt-5">
        <div class="row justify-content-md-center">
            <div class="col-md-4">
                <div class="card profile-card bg-light">
                    <div class="card-body text-center">
                        <img src="${user.profilepicPath}" class="profile-picture img-fluid mb-3" alt="Profile Picture">
                        <h5 class="card-title">${user.fname} ${user.lname}</h5>
                        <p class="card-text text-muted">Username : ${user.username}</p>
                        <p class="card-text text-muted">Gender: ${user.gender}</p>
                        <p class="card-text text-muted">Date of Birth: ${user.dob}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row justify-content-md-center">
            <div class="col-md-8 mt-5">
                <div class="card bio-card bg-light">
                    <div class="card-body">
                        <h5 class="card-title">Bio</h5>
                        <p class="card-text">About Me: ${user.aboutMe}</p>
                        <hr>
                        <p class="card-text">Role: ${user.role}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>