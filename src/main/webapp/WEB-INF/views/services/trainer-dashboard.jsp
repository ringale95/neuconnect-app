<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trainer Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="module" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>

<body>
    <cm:header />

    <div class="container">
        <div class="row">
            <!-- Left Section -->
            <div class="col-md-3 border" style="padding: 40px; margin-top: 120px; margin-right: 15px;">
                <div class="text-center">
                    <img src="${pageContext.request.contextPath}${user.profilepicPath}" alt="Trainer"
                        class="img-fluid rounded-circle" style="width: 200px;">
                    <h2 class="mt-5">${user.fname} ${user.lname}
                        <c:if test="${verifiedTrainer}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="green"
                                class="bi bi-patch-check-fill" viewBox="0 0 16 16">
                                <path
                                    d="M10.067.87a2.89 2.89 0 0 0-4.134 0l-.622.638-.89-.011a2.89 2.89 0 0 0-2.924 2.924l.01.89-.636.622a2.89 2.89 0 0 0 0 4.134l.637.622-.011.89a2.89 2.89 0 0 0 2.924 2.924l.89-.01.622.636a2.89 2.89 0 0 0 4.134 0l.622-.637.89.011a2.89 2.89 0 0 0 2.924-2.924l-.01-.89.636-.622a2.89 2.89 0 0 0 0-4.134l-.637-.622.011-.89a2.89 2.89 0 0 0-2.924-2.924l-.89.01zm.287 5.984-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7 8.793l2.646-2.647a.5.5 0 0 1 .708.708" />
                            </svg>
                        </c:if>
                    </h2>
                    <p>Karma Points: ${user.karma}</p>
                </div>
            </div>
            <!-- Right Section -->
            <div class="col-md-8">
                <!-- Down Section -->
                <div class="row border" style="padding: 40px; margin-top: 120px;">
                    <div class="col-md-12">
                        <h3 class="mt-4">Certificate Upload</h3>
                        <form id="upload-certificate-form">
                            <div class="mb-3">
                                <label for="type" class="form-label">Document Type</label>
                                <select class="form-select" id="type" name="type">
                                    <c:forEach items="${serviceTypes}" var="type">
                                        <option value="${type}">${type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="file" class="form-label">Upload Certificate (PDF)</label>
                                <input class="form-control" type="file" id="file" name="file" accept="application/pdf">
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>

                <!-- Up Section -->
                <c:if test="${verifiedTrainer}">
                    <div class="row border" style="padding: 40px; margin-top: 15px;">
                        <div class="col-md-12">
                            <h3 class="mt-4">Session Section</h3>
                            <div>
                                <a href="/services/session-form" class="btn btn-primary m-2">Start Sessions</a>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>