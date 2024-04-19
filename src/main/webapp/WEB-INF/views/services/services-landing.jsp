<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="cm" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Landing Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        img {
          border-radius: 10%;
        }

        /* Hero Section */
        .hero-section {
            background: linear-gradient(to right, red, #3632a7);
            color: white;
            padding: 300px 0;
            text-align: center;
            transition: background-color 0.5s, color 0.5s;
        }

        /* Service Section */
        .service-section {
            padding: 80px 0; /* Increased space between sections */
            transition: background-color 0.5s, color 0.5s;
        }

        .service-section .row {
            align-items: center;
        }

        .service-section .service-img {
            padding: 0 15px;
            transition: transform 0.5s;
            border-radius: 15px; /* Rounded borders */
            overflow: hidden; /* Prevents image overflow */
        }

        .service-section .service-img img {
            width: 100%; /* Make images responsive */
            height: auto;
        }

        .service-section .service-info {
            padding: 0 30px;
            transition: transform 0.5s;
        }

        .service-section .service-info ul li a {
            color: #4CAF50; /* Change link color */
            transition: color 0.3s;
        }

        .service-section .service-info ul li a:hover {
            color: #ff6666; /* Change link color on hover */
        }

        /* Apply transition on hover */
        .service-section:hover .service-img {
            transform: scale(1.05);
        }

        .service-section:hover .service-info {
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <cm:header/>
    <section class="hero-section">
        <div class="container">
            <h1 class="display-2">Welcome to our Services Page!</h1>
            <p class="display-6">NEUConnect offers a diverse range of services across different aspects of life. Connect with your peers to access and benefit from these varied services.</p>
        </div>
    </section>

    <!-- Fitness Section -->
    <section class="service-section">
        <div class="container">
            <div class="row">
                <div class="col-md-6 order-md-1 service-img rounded"> <!-- Added rounded class -->
                    <img src="${pageContext.request.contextPath}/images/fitness-section.png" alt="Fitness Image" class="img-fluid">
                </div>
                <div class="col-md-6 order-md-2 service-info">
                    <h2>Fitness</h2>
                    <p>Get fit with our professional trainers.</p>
                    <ul>
                        <li><a href="/services/services-list">Register for a Session</a></li>
                        <li><a href="/services/trainer-dashboard">If you are a Fitness Consultant? &gt; </a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- Tutoring Section -->
    <section class="service-section bg-light">
        <div class="container">
            <div class="row">
                <div class="col-md-6 service-info">
                    <h2>Tutoring</h2>
                    <p>Improve your skills with our experienced tutors.</p>
                    <ul>
                        <li><a href="/services/tutor-dashboard">If you are a Tutor?</a></li>
                        <li><a href="/services/tutoring-list">Register for Tutoring</a></li>
                    </ul>
                </div>
                <div class="col-md-6 service-img rounded"> <!-- Added rounded class -->
                    <img src="${pageContext.request.contextPath}/images/tutoring-section.png" alt="Tutoring Image" class="img-fluid">
                </div>
            </div>
        </div>
    </section>

    <!-- Career Consultation Section -->
    <section class="service-section">
        <div class="container">
            <div class="row">
                <div class="col-md-6 order-md-1 service-img rounded"> <!-- Added rounded class -->
                    <img src="${pageContext.request.contextPath}/images/careers-section.png" alt="Career Consultation Image" class="img-fluid">
                </div>
                <div class="col-md-6 order-md-2 service-info">
                    <h2>Career Consultation</h2>
                    <p>Plan your career with our expert consultants.</p>
                    <ul>
                        <li><a href="/services/career-dashboard">If you are a career consultant?</a></li>
                        <li><a href="/services/career-list">Register for Career Consultation</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
