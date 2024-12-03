<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <!-- Pico CSS Minimal Framework -->
    <link href="https://unpkg.com/@picocss/pico@latest/css/pico.min.css" rel="stylesheet">
    <style>
        .form-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height */
        }
        .form-wrapper {
            width: 33%;
            min-width: 300px;
        }
        /* Toast banner for error message */
        .toast-banner {
            background-color: #f44336; /* Red background for errors */
            color: white;
            padding: 10px;
            border-radius: 5px;
            text-align: center;
            margin-bottom: 15px;
            display: none; /* Hidden by default */
        }
        /* Show the toast if there is an error message */
        .show-toast {
            display: block;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main class="container">
    <div class="form-container">
        <div class="form-wrapper">
            <h1>Profile</h1>
        </div>
    </div>
</main>
</body>
</html>
