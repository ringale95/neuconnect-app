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
    <style>
        /* Add your custom styles here */
    </style>
</head>

<body>
    <cm:header />
    <div class="container mt-4">
        <h2>Feed List</h2>
        <ul class="list-group mt-3">
            <li class="list-group-item">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="mb-0">Feed Item 1</h5>
                        <p class="text-muted mb-0">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                    </div>
                    <div>
                        <span class="badge bg-primary">New</span>
                    </div>
                </div>
            </li>
            <li class="list-group-item">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="mb-0">Feed Item 2</h5>
                        <p class="text-muted mb-0">Pellentesque habitant morbi tristique senectus et netus et malesuada
                            fames ac turpis egestas.</p>
                    </div>
                    <div>
                        <span class="badge bg-warning text-dark">Updated</span>
                    </div>
                </div>
            </li>
            <li class="list-group-item">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="mb-0">Feed Item 3</h5>
                        <p class="text-muted mb-0">Donec ac ex eu nisl rhoncus viverra nec at neque.</p>
                    </div>
                    <div>
                        <span class="badge bg-success">Completed</span>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>