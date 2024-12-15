<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Confirmation</title>
    <!-- Pico CSS Minimal Framework -->
    <link href="https://unpkg.com/@picocss/pico@latest/css/pico.min.css" rel="stylesheet">
    <style>
        .message-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 60vh; 
        }
        .message-wrapper {
            width: 80%;
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
<header>
    <jsp:include page="header.jsp"/>
</header>
<main class="container">
    <div class="message-container">
        <div class="message-wrapper">
            <!-- Unconfirmed Registration Message -->
            <c:if test="${unconfirmedRegistration}">
                <div class="message info">
                    <p>Your account is not confirmed. Please check your email to activate your account.</p>
                    <p>
                        <a href="${pageContext.request.contextPath}/controller?command=RESEND_CONFIRMATION_MAIL">Resend Confirmation Email</a>
                    </p>
                </div>
            </c:if>

            <!-- Banned User Message -->
            <c:if test="${userBanned}">
                <div class="message error">
                    <p>Your account has been banned. Contact support for further assistance.</p>
                </div>
            </c:if>

            <c:if test="${unconfirmedRegistrationResend}">
                <div class="message info">
                    <p>A new confirmation email has been sent to your registered email address. Please check your inbox and follow the link to activate your account. If you don't see it, be sure to check your spam folder.</p>
                </div>
            </c:if>

            <!-- Fallback Navigation -->
            <p><a href="${pageContext.request.contextPath}/jsp/login.jsp" class="contrast">Back to Login</a></p>
        </div>
    </div>
</main>
</body>
</html>