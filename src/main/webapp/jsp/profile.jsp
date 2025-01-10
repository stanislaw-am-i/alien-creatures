<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.userLocale}" />
<fmt:setBundle basename="locale.messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link href="https://unpkg.com/@picocss/pico@latest/css/pico.min.css" rel="stylesheet">
    <style>
        .form-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height */
        }
        .form-wrapper {
            width: 50%;
            min-width: 300px;
        }
        .toast-banner {
            background-color: #f44336; /* Red background for errors */
            color: white;
            padding: 10px;
            border-radius: 5px;
            text-align: center;
            margin-bottom: 15px;
            display: none; /* Hidden by default */
        }
        .show-toast {
            display: block;
        }
    </style>
    <script>
        function enableEditing() {
            const fields = document.querySelectorAll('.editable');
            fields.forEach(field => field.removeAttribute('disabled'));
            document.getElementById('edit-button').style.display = 'none';
            document.getElementById('save-button').style.display = 'inline';
        }
    </script>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main class="container">
    <div class="form-container">
        <div class="form-wrapper">
            <h1>Edit Profile</h1>

            <!-- Error Message Toast Banner -->
            <div class="toast-banner ${!empty errorMessage ? 'show-toast' : ''}">
                ${errorMessage}
            </div>

            <c:if test="${isSuccessMessage == true}">
                <div class="success-message">
                    <fmt:message key="change_profile" />
                </div>
            </c:if>

            <!-- Profile Form -->
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="CHANGE_PROFILE_DATA"/>

                <label for="username">Username</label>
                <input type="text" id="username" name="username" value="${sessionScope.user_name}" class="editable" disabled required/>

                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="${sessionScope.email}" class="editable" disabled required/>
                
                <button type="button" id="edit-button" onclick="enableEditing()">Edit</button>
                <input type="submit" id="save-button" value="Save" style="display: none;" class="contrast"/>
            </form>
        </div>
    </div>
</main>
</body>
</html>