<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Notification List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <script type="module" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>

<body>
  <cm:header />

  <div class="container mt-4">
    <div class="row mb-3">
      <div class="col-auto">
        <select class="form-select" id="filterSelect">
          <option value="ALL">All</option>
          <option value="UNREAD">Unread</option>
        </select>
      </div>

    </div>

    <div id="notificationList"></div>
  </div>
</body>

</html>