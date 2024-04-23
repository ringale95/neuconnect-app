<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authority Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <cm:header />

    <div class="container mt-5">
        <!-- Certificate Approval Table -->
        <div class="card">
            <div class="card-header">
                Certificate Approval List
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Request Author</th>
                            <th>Attached Certificate</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="request" items="${requests}">
                            <tr>
                                <td>${request.author.fname} ${request.author.lname}</td>
                                <td><a
                                        href="/user-dashboard/${request.certificateAttached.id}/certificates">${request.certificateAttached.type}</a>
                                </td>
                                <td>
                                    <a href="/user-dashboard/${request.id}/services/approve"
                                        class="btn btn-success">Approve</a>
                                    <a href="/user-dashboard/${request.id}/services/reject"
                                        class="btn btn-danger">Reject</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <!-- End of example data -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>