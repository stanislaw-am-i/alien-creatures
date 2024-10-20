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
<main class="container">
    <div class="form-container">
        <div class="form-wrapper">
            <h1>Register</h1>

            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="SIGN_UP"/>

                <!-- Error Message Toast Banner -->
                <div class="toast-banner ${!empty errorRegistrationMessage ? 'show-toast' : ''}">
                    ${errorRegistrationMessage}
                </div>

                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Choose a username" required/>

                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required/>

                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter a password" required/>

                <label for="confirmPassword">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" required/>

                <input type="submit" value="Register" class="contrast"/>
            </form>

            <p>Already have an account? <a href="${pageContext.request.contextPath}/jsp/login.jsp">Login here</a>.</p>
        </div>
    </div>
</main>
</body>
</html>