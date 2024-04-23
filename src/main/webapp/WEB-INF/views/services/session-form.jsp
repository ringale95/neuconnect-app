<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <cm:header />
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        Service Form
                    </div>
                    <div class="card-body">
                        <form action="/services/session-form" method="POST">
                            <div class="mb-3">
                                <label for="serviceType" class="form-label">Service Type</label>
                                <select class="form-select" id="serviceType" name="serviceType">
                                    <option value="Individual">Individual</option>
                                    <option value="Multi-request">Multi-request</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="title" class="form-label">Title</label>
                                <input type="text" class="form-control" id="title" name="title" required>
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Description</label>
                                <textarea class="form-control" id="description" name="description" rows="3"
                                    required></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="karma" class="form-label">Karma</label>
                                <input type="number" class="form-control" id="karma" name="karma" required>
                            </div>
                            <div class="mb-3">
                                <label for="karma" class="form-label">Category</label>
                                <select class="form-select" id="type" name="type">
                                    <option value="FITNESS">FITNESS</option>
                                    <option value="TUTORING">TUTORING</option>
                                    <option value="DANCER">DANCER</option>
                                    <option value="CAREERCONSULTANT">CAREERCONSULTANT</option>
                                    <option value="OTHER">OTHER</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>