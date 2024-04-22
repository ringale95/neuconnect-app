<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>NeuConnect</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .profile-pic-preview {
      max-width: 150px;
      height: auto;
      border-radius: 50%;
    }
  </style>
</head>

<body>

  <div class="container-md mt-5">
    <h2 class="mb-4">Register for NeuConnect!</h2>
    <form action="/register-action" method="POST" enctype="multipart/form-data">
      <div class="mb-3">
        <label for="profilePicture" class="form-label">Profile Picture</label>
        <input class="form-control" type="file" id="profilePicture" name="profilePicture">
      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="firstName" class="form-label">First Name</label>
          <input type="text" class="form-control" id="firstName" name="firstName" required>
        </div>
        <div class="col-md-6 mb-3">
          <label for="lastName" class="form-label">Last Name</label>
          <input type="text" class="form-control" id="lastName" name="lastName" required>
        </div>
      </div>
      <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input type="text" class="form-control" id="username" name="username" required>
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" name="password" required>
      </div>
      <div class="mb-3">
        <label for="dob" class="form-label">Date of Birth</label>
        <input type="date" class="form-control" id="dob" name="dob" required>
      </div>
      <div class="mb-3">
        <label for="dob" class="form-label">nuId</label>
        <input type="text" class="form-control" id="nuId" name="nuId" required>
      </div>
      <div class="mb-3">
        <label for="gender" class="form-label">Gender</label>
        <select class="form-select" id="gender" name="gender" required>
          <option value="">Select Gender</option>
          <option value="male">Male</option>
          <option value="female">Female</option>
          <option value="other">Other</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="aboutMe" class="form-label">About Me</label>
        <textarea class="form-control" id="aboutMe" name="aboutMe" rows="3" required></textarea>
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>

    <c:if test="${status eq 'FAILED'}">
      <div class="alert alert-danger mt-4">
        Registration failed! Please check the provided information.
      </div>
    </c:if>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js"></script>
  <script>
    // Preview Profile Picture
    document.getElementById('profilePicture').addEventListener('change', function () {
      var file = this.files[0];
      if (file) {
        var reader = new FileReader();
        reader.onload = function (event) {
          var img = document.querySelector('.profile-pic-preview');
          img.src = event.target.result;
          img.classList.remove('d-none');
        }
        reader.readAsDataURL(file);
      }
    });
  </script>

</body>

</html>