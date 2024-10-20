<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Server Error</title>
    <!-- Pico CSS Minimal Framework -->
    <link href="https://unpkg.com/@picocss/pico@latest/css/pico.min.css" rel="stylesheet">
    <style>
        /* Custom styling for error page */
        .error-container {
            margin: 0 auto;
            padding: 40px;
            text-align: center;
        }
        .error-banner {
            background-color: #f44336; /* Red for error */
            color: white;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .error-details {
            text-align: left;
        }
    </style>
</head>
<body>
<main class="container">
    <div class="error-container">
        <!-- Error message displayed prominently -->
        <div class="error-banner">
            <h1>500 - Server Error</h1>
            <p>${error_msg != null ? error_msg : "An unexpected error occurred. Please try again later."}</p>
        </div>

        <p><b>Error Details:</b></p>
        <ul class="error-details">
            <li><strong>Request URI:</strong> ${pageContext.errorData.requestURI}</li>
            <li><strong>Status code:</strong> ${pageContext.errorData.statusCode}</li>
            <li><strong>Servlet name:</strong> ${pageContext.errorData.servletName}</li>
            <li><strong>Exception:</strong> ${pageContext.exception}</li>
            <li><strong>Message from exception:</strong> ${pageContext.exception.message}</li>
        </ul>

        <!-- Link back to home page -->
        <a href="${pageContext.request.contextPath}" role="button" class="contrast">Go to Home</a>
    </div>
</main>
</body>
</html>