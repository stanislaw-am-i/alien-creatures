<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Confirmation</title>
    <link href="https://unpkg.com/@picocss/pico@latest/css/pico.min.css" rel="stylesheet">
    <style>
        .message-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height */
        }
        .message-wrapper {
            width: 50%;
            min-width: 300px;
            text-align: center;
        }
        .toast-banner {
            color: white;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            font-size: 1.1em;
        }
        .show-error-toast {
            background-color: green !important;
            display: block;
        }
        .show-error-toast {
            background-color: #f44336 !important;
            display: block;
        }
    </style>
</head>
<body>
<main class="container">
    <div class="message-container">
        <div class="message-wrapper">
            <h1>Registration Confirmation</h1>
            <div class="toast-banner ${!empty errorRegistrationMessage ? 'show-error-toast' : ''}">
                ${errorRegistrationMessage}
            </div>

            <div class="toast-banner ${!empty confirmationMessage ? 'show-toast' : ''}">
                ${confirmationMessage}
            </div>

            <p>Thank you for registering! A confirmation email has been sent to your email address. Please check your inbox and click the confirmation link to activate your account.</p>

            <p><a href="${pageContext.request.contextPath}/jsp/login.jsp" class="contrast">Back to Login</a></p>
        </div>
    </div>
</main>
</body>
</html>
