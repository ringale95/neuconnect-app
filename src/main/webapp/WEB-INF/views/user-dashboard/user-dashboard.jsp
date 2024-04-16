<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
   <cm:header/>

    <div class="container mt-4">
            <div class="container mt-4">
                <div class="row justify-content-center">
                   <div class="col-md-2 mt-5">
                       <div class="card">
                           <img src="${pageContext.request.contextPath}/images/manage-users.png" class="card-img-top" alt="Manage Users Image">
                           <div class="card-body">
                               <h5 class="card-title"> <a href="/user-dashboard/manage-users">Manage Users</a></h5>
                           </div>
                       </div>
                   </div>

                    <div class="col-md-2 mt-5">
                      <div class="card">
                          <img src="${pageContext.request.contextPath}/images/manage-users.png" class="card-img-top" alt="Manage Users Image">
                          <div class="card-body">
                              <h5 class="card-title"> <a href="/user-dashboard/manage-users">Manage Users</a></h5>
                          </div>
                      </div>
                </div>
               </div>
           </div>
    </div>
    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
