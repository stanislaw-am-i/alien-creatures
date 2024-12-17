<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>Alien Characters</title>
    <link rel="stylesheet" href="https://unpkg.com/@picocss/pico@1.5.7/css/pico.min.css">
    <style>
        .error-message {
            color: #f44336;
            margin-bottom: 15px;
        }
        .success-message {
            color: #218838;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<main class="container">
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <section>
        <h2>Add New Alien Character</h2>
        <form method="POST" action="${pageContext.request.contextPath}/controller" enctype="multipart/form-data">
            <input type="hidden" name="command" value="add_alien"/>

            <p class="error-message">${errorPassMessage}</p>

            <c:if test="${isSuccessMessage == true}">
                <div class="success-message">
                    <fmt:message key="add_new_alien" />
                </div>
            </c:if>
            <c:if test="${isFailedMessage == true}">
                <div class="error-message">
                    <fmt:message key="add_new_alien_failed" />
                </div>
            </c:if>

            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="" required />

            <label for="lor">Lor</label>
            <input type="text" id="lor" name="lor" value="" required />

            <label for="file">Choose Image</label>
            <input type="file" id="file" name="file" />

            <button type="submit">Add Character</button>
        </form>
    </section>
</main>
</body>
</html>