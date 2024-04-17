<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>NEUKarma - Social Platform</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Custom CSS for animations */
    .fade-in {
      animation: fadeIn 1s ease-in-out;
    }

    @keyframes fadeIn {
      0% {
        opacity: 0;
      }
      100% {
        opacity: 1;
      }
    }

    .hero {
      background-size: cover;
      background-position: center;
      color: #fff;
      height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .hero-content {
      text-align: center;
    }

    .feature-tile {
      padding: 40px 20px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      transition: transform 0.3s ease;
    }

    .feature-tile:hover {
      transform: translateY(-5px);
    }
  </style>
</head>
<body>

<!-- Navbar -->
<div style="background: linear-gradient(rgba(255,0,0,0.5), rgba(43,0,255,0.5)), url('');">
<nav class="navbar navbar-expand-lg">
  <div class="container">
    <a class="navbar-brand" href="#"><span style="color:red;">NEU</span>Karma</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="/register">Register</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/login">Login</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!-- Main Hero Section -->
<section class="hero">
  <div class="container">
    <div class="hero-content fade-in">
      <h1 class="display-1 mb-4 text-dark">Welcome to <span style="color:red;">NEU</span>Karma</h1>
      <p class="display-4">A comprehensive social platform designed to foster collaboration, knowledge sharing, and community engagement within Northeastern University and its surrounding community.</p>
    </div>
  </div>
</section>
</div>

<!-- Features Section -->
<section class="features py-5">
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <div class="feature-tile text-center mb-4 fade-in">
          <h3 class="mb-3">Karma System</h3>
          <p>Earn karma points by assisting other users.</p>
        </div>
      </div>
      <div class="col-md-6">
        <div class="feature-tile text-center mb-4 fade-in">
          <h3 class="mb-3">Service Section</h3>
          <p>Create and offer services to others.</p>
        </div>
      </div>
      <div class="col-md-6">
        <div class="feature-tile text-center mb-4 fade-in">
          <h3 class="mb-3">Marketplace</h3>
          <p>Exchange karma points for rewards.</p>
        </div>
      </div>
      <div class="col-md-6">
        <div class="feature-tile text-center mb-4 fade-in">
          <h3 class="mb-3">Feed Section</h3>
          <p>Personalized feed with relevant posts and interactions.</p>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Custom JS for fade-in animation -->
<script>
  document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.fade-in').forEach(function (element) {
      element.classList.add('fade-in');
    });
  });
</script>

</body>
</html>
