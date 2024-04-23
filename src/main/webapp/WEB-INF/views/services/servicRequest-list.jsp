<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Request</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
        crossorigin="anonymous">
    <script type="module" src="${pageContext.request.contextPath}/js/index.js"></script>

    <style>
        /* Custom Styles */
        .card {
            border: none;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card-body {
            padding: 20px;
            background-color: light-grey;
        }

        .card-title {
            font-weight: bold;
            font-size: 1.4rem;
            margin-bottom: 15px;
            color: #007bff;
            /* Blue color */
        }

        .card-text {
            margin-bottom: 10px;
            color: #6c757d;
            /* Gray color */
        }

        /* Filter Section */
        #filter-submit-form {
            margin-top: 30px;
        }

        #filter-submit-form h5 {
            color: black;
            /* Blue color */
            font-size: 1.2rem;
            margin-bottom: 20px;
        }

        #filter-submit-form .form-label {
            font-weight: bold;
            color: black;
            /* Blue color */
        }

        #filter-submit-form .btn-primary {
            background-color: black;
            /* Blue color */
            border-color: black;
            /* Blue color */
        }

        #filter-submit-form .btn-primary:hover {
            background-color: #0056b3;
            /* Darker blue color */
            border-color: black;
            /* Darker blue color */
        }
    </style>
</head>

<body>
    <cm:header />
    <div class="container">
        <div class="row">
            <div class="col-md-3 border" style="padding: 40px; margin-top: 100px;">
                <div class="mb-3">
                    <form id="filter-submit-form">
                        <h5>Filter</h5>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" name="searchKey" placeholder="Search"
                                aria-label="Search" aria-describedby="basic-addon2">
                        </div>
                        <div class="mb-3">
                            <label for="typeSelect" class="form-label">Type</label>
                            <select class="form-select" id="typeSelect" name="type">
                                <option selected></option>
                                <c:forEach items="${types}" var="type">
                                    <option value="${type}">${type}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="minKarma" class="form-label">Min Karma</label>
                            <input type="number" class="form-control" id="minKarma" name="karmaMin">
                        </div>
                        <div class="mb-3">
                            <label for="maxKarma" class="form-label">Max Karma</label>
                            <input type="number" class="form-control" id="maxKarma" name="karmaMax">
                        </div>
                        <div class="mb-3">
                            <label for="sortBy" class="form-label">Sort By</label>
                            <select class="form-select" id="sortBy" name="sortOption">
                                <option selected></option>
                                <c:forEach items="${sortBys}" var="type">
                                    <option value="${type}">${type}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary">Search</button>
                        </div>
                    </form>
                </div>

            </div>

            <!-- Right Column -->
            <div class="col-md-8">
                <div class="row" style="padding: 40px;">
                    <div class="col-md-12" style="margin-top: 100px;">
                        <h2 class="display-5 mb-2"> Search Results: </h2>
                        <hr />
                        <div id="serviceListEntryPointForJS"></div>
                    </div>
                    <div class="col text-end">
                        <button id="previous" class="btn btn-link">&lt; Previous </button>
                        <button id="next" class="btn btn-link">Next &gt; </button>
                    </div>
                    <div style="display:none;" id="pageNumber"></div>
                    <div style="display:none;" id="pageCount"></div>
                </div>
            </div>
        </div>
        <!-- Bootstrap Bundle JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"></script>
</body>

</html>