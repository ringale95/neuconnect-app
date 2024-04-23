<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Request Summary</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .custom-card {
            background-color: #f8f9fa;
            border-radius: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .custom-card-header {
            background-color: #007bff;
            color: #fff;
            border-top-left-radius: 20px;
            border-top-right-radius: 20px;
        }

        .server-list {
            max-height: 200px;
            overflow-y: auto;
        }
    </style>
</head>

<body>
    <cm:header />

    <div class="container mt-5">
        <h1 class="mb-4">Service Request Summary</h1>
        <div class="row">
            <div class="col-md-8">
                <div class="card custom-card">
                    <div class="card-header custom-card-header">
                        Service Request Summary
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Service Request Title: ${req.title}</h5>
                        <p class="card-text">Description of the Service Request : ${req.description}</p>
                        <p class="card-text">Raised By: ${req.author.fname} ${req.author.lname}</p>
                        <p class="card-text">Karma: ${req.karma}</p>
                        <p class="card-text">Status : ${req.status}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 mt-5">
                <div class="card custom-card">
                    <div class="card-header custom-card-header">
                        Assigned Servers
                    </div>
                    <div class="card-body">
                        <ul class="list-group server-list">
                            <c:forEach var="server" items="${servers}">
                                <li class="list-group-item">${server.fname} ${server.lname}</li>
                            </c:forEach>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>