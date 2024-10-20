<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Pico CSS Minimal Framework -->
    <link href="https://unpkg.com/@picocss/pico@latest/css/pico.min.css" rel="stylesheet">
    <style>
        /* Centering the form and adding styles */
        .form-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height */
        }
        .form-wrapper {
            width: 100%;
            max-width: 400px; /* Form width */
            padding: 20px;
            border-radius: 8px;
            background-color: var(--background-muted); /* Light background for the form */
        }
        .error-message {
            color: #f44336; /* Red for error messages */
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<main class="container">
    <div class="form-container">
        <div class="form-wrapper">
            <h1>Login</h1>
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="Login"/>

                <!-- Login Input -->
                <label for="login">Login</label>
                <input type="text" id="login" name="login" placeholder="Enter your login" required/>

                <!-- Password Input -->
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required/>

                <!-- Error message display -->
                <div class="error-message">
                    ${errorLoginPassMessage != null ? errorLoginPassMessage : ""}
                </div>

                <!-- Login Button -->
                <input type="submit" value="Login" class="contrast"/>

            </form>
            <hr/>
            <p>Don't have an account? <a href="${pageContext.request.contextPath}/jsp/register.jsp">Register here</a>.</p>
        </div>
    </div>
</main>

</body>
</html>