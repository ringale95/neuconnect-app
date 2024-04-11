<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Registration Page</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/js/index.js"></script>

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet">
</head>
<body>
 <div class="container-fluid">
    <div class="row">
      <div class="col-8">
        <img src="${pageContext.request.contextPath}/images/img_2.png" alt="Image" class="custom-image">
      </div>
      <div class="col-4 d-flex justify-content-center" style="align-items: center;">
        <form>
          <img src="${pageContext.request.contextPath}/images/neu-header-logo.png" alt="Image" class="custom-image">
          <h4 class="mt-3 mb-3">Sign in</h4>
          <div class="mb-3">
            <label for="exampleInputName" class="form-label">Name</label>
            <input type="text" class="form-control" id="exampleInputName">
          </div>
          <div class="mb-3">
            <label for="exampleInputEmail" class="form-label">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail">
          </div>
          <div class="mb-3">
            <label for="exampleInputPassword" class="form-label">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword">
          </div>
          <button type="submit" class="btn btn-primary">Register</button>
        </form>
      </div>
      <div>
        Please sign in using your Northeastern username@northeastern.edu or email address and password.
      </div>
    </div>
  </div>
</body>
</html>
