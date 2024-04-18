<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Access Denied</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-lshntDkYsB7u+pL9/jjR5ixLJzEALfFq0X+XkWHRhzGr/lIsOsQiRiWrVS/nFRQi" crossorigin="anonymous">
    <!-- Custom CSS -->
    <style>
        .container {
            margin-top: 50px;
            text-align: center;
        }

        img {
            width: 300px;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>
    <div class="container">
        <img src="${pageContext.request.contextPath}/images/access-denied.png" alt="Access Denied" class="img-fluid">
        <h1>Access Denied!</h1>
        <p>Sorry, you don't have permission to access this page.</p>
        <a href="/" class="btn btn-primary">Go Back Home</a>
    </div>

    <!-- Bootstrap JS (optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Lfv4JIMDo1E7HCrH6eLtwafaxOjJdFfCgQqyjSuhfD8Y+FN1h67CSbcwAM1pF6El" crossorigin="anonymous">
    </script>
</body>

</html>
