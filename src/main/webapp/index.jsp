<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
        }
        h1 {
            color: #007bff;
            text-align: center;
            margin-bottom: 30px;
        }
        .custom-link {
            display: block;
            padding: 10px;
            background-color: #ffffff;
            border: 1px solid #dee2e6;
            margin-bottom: 10px;
            color: #007bff;
            text-decoration: none;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        .custom-link:hover {
            background-color: #007bff;
            color: #ffffff;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1>Index</h1>
    <a href="customerListing.jsp" class="custom-link">Customer Listing</a>
    <a href="products.jsp" class="custom-link">Product Listing</a>
    <a href="login.jsp" class="custom-link">Login</a>
    <!-- Add more links as needed -->
</div>

<!-- Bootstrap JS and Popper.js -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
