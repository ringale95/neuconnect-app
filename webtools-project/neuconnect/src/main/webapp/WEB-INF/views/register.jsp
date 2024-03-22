<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>CRS</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">



    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>


    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet">
  </head>
  <body class="text-center">

<main class="form-signin">
  <form id="register-form" method="post" action="/register">
    <h1 class="h3 mb-3 fw-normal">Register here for NEUConnect!</h1>

    <div class="form-floating">
      <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" required>
      <label for="firstName">First Name</label>
    </div>
    <div class="form-floating">
      <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required>
      <label for="lastName">Last Name</label>
    </div>
    <div class="form-floating">
          <input type="text" class="form-control" id="nuId" name="nuId" placeholder="NU ID" required>
          <label for="nuId">NU ID</label>
        </div>
        <div class="form-floating">
          <input type="date" class="form-control" id="dob" name="dob" required>
          <label for="dob">Date of Birth</label>
        </div>
        <div class="form-floating">
          <select class="form-select" id="gender" name="gender" required>
            <option value="">Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
          </select>
          <label for="gender">Gender</label>
        </div>
    <div class="form-floating">
      <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com" required>
      <label for="email">Email address</label>
    </div>
    <div class="form-floating">
      <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
      <label for="username">Username</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
      <label for="password">Password</label>
    </div>


    <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
  </form>
  <c:if test="${status eq 'FAILED'}">
      <div class="alert alert-danger mt-4">
          Registration failed! Please check the provided information.
      </div>
  </c:if>
</main>
<script type="module" src="${pageContext.request.contextPath}/js/index.js"></script>

